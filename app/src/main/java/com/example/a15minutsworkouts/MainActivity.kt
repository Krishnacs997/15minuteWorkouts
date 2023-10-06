package com.example.a15minutsworkouts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.a15minutsworkouts.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {
    private var binding : MainActivityBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.flstart?.setOnClickListener{
            Toast.makeText(this@MainActivity, "Click", Toast.LENGTH_SHORT ).show()

            val intent = Intent(this, WorkoutList::class.java)
            startActivity(intent)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}