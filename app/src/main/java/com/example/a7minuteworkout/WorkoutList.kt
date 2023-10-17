package com.example.a7minuteworkout

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minuteworkout.databinding.ActivityWorkoutListBinding
import java.util.*
import kotlin.collections.ArrayList

class WorkoutList : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var binding : ActivityWorkoutListBinding? = null
    private var restTimer : CountDownTimer? = null
    private var restProgress = 0

    private var exerciseRestTimer : CountDownTimer? = null
    private var exerciseRestProgress = 0

    private  var tts : TextToSpeech? = null
    private var player : MediaPlayer? = null

    private var exerciseStatusAdapter : ExerciseStatusAdapter? = null

    private var exerciseList : ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWorkoutListBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarExercise)

        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        exerciseList = Constants.defaultExerciseList()

        binding?.toolbarExercise?.setNavigationOnClickListener{
            onBackPressed()
        }
        binding?.toolbarExercise?.visibility = View.INVISIBLE
        setUpRestView()
        setupExercisesStatusRecyclerView()

        tts = TextToSpeech(this, this)
    }
    private  fun setupExercisesStatusRecyclerView(){
        binding?.listrecyclerview?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        exerciseStatusAdapter = ExerciseStatusAdapter(exerciseList!!)
        binding?.listrecyclerview?.adapter = exerciseStatusAdapter
    }
    private fun setUpRestView(){

        try {
            var soundURI = Uri.parse("android.resource://com.example.a7minuteworkout/" + R.raw.press_start)
            player = MediaPlayer.create(applicationContext, soundURI)
            player?.isLooping = false
            player?.start()
        }catch (e: Exception){
            e.printStackTrace()
        }

        binding?.flProgressBar?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.tvExercise?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility = View.INVISIBLE
        binding?.tvImage?.visibility = View.INVISIBLE

        if(restTimer != null ){
            restTimer?.cancel()
            restProgress = 0
        }
        setRestpProgressBar()
    }

    private fun setUpExerciseView(){
        binding?.flProgressBar?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE
        binding?.tvExercise?.visibility = View.VISIBLE
        binding?.flExerciseView?.visibility = View.VISIBLE
        binding?.tvImage?.visibility = View.VISIBLE
        if(exerciseRestTimer != null){
            exerciseRestTimer?.cancel()
            exerciseRestProgress = 0
        }

        speakOut(exerciseList!![currentExercisePosition].getName())

        binding?.tvImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExercise?.text = exerciseList!![currentExercisePosition].getName()
        setExerciseProgressBar()
    }

    private  fun setRestpProgressBar(){
        binding?.progressBar?.progress = restProgress
        restTimer = object : CountDownTimer(10000, 1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.progressBar?.progress = 10-restProgress
                binding?.tvTimer?.text=(10 - restProgress).toString()
            }

            override fun onFinish() {
                currentExercisePosition++
                setUpExerciseView()
            }

        }.start()

    }

    private  fun setExerciseProgressBar(){
        binding?.progrbarExercise?.progress = exerciseRestProgress
        exerciseRestTimer = object : CountDownTimer(30000, 1000){
            override fun onTick(p0: Long) {
                exerciseRestProgress++
                binding?.progrbarExercise?.progress = 30-exerciseRestProgress
                binding?.tvTimerExercise?.text=(30 - exerciseRestProgress).toString()
            }

            override fun onFinish() {
                if(currentExercisePosition <exerciseList?.size!! - 1){
                    setUpRestView()
                }else{
                    Toast.makeText(this@WorkoutList, "Congratulation you have complited the & minutes workout", Toast.LENGTH_SHORT).show()
                }
            }

        }.start()

    }


    override fun onDestroy() {
        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }

        // TODO (Step 7 - Shutting down the Text to Speech feature when activity is destroyed.)
        // START
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        // END
        super.onDestroy()
        binding = null
    }

    override fun onInit(status: Int) {
         if(status == TextToSpeech.SUCCESS ){
            val result = tts?.setLanguage(Locale.US)
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("tts", "specified language not supported")
            }
        }else{
            Log.e("tts", "Language not initialized")
        }
    }

    private fun speakOut(text: String){
        tts!!.speak(text, TextToSpeech.QUEUE_ADD, null, "")

    }
}