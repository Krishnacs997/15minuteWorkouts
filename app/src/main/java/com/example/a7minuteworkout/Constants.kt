package com.example.a7minuteworkout

object Constants {
    fun defaultExerciseList(): ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()
        val jumpingJacs = ExerciseModel(
            1,
            "Jumping Jacks",
            R.drawable.img_4,
            false,
            false
        )
        exerciseList.add(jumpingJacs)

        val jumpingJacs1 = ExerciseModel(
            2,
            "Jumping krishna",
            R.drawable.img_1,
            false,
            false
        )
        exerciseList.add(jumpingJacs1)

        val jumpingJacs2 = ExerciseModel(
            3,
            "Jumping yogesh",
            R.drawable.img_2,
            false,
            false
        )
        exerciseList.add(jumpingJacs2)

        val jumpingJacs3 = ExerciseModel(
            4,
            "Jumping kamlesh",
            R.drawable.img_3,
            false,
            false
        )
        exerciseList.add(jumpingJacs3)

        val jumpingJacs4 = ExerciseModel(
            5,
            "Jumping Raja",
            R.drawable.img_4,
            false,
            false
        )
        exerciseList.add(jumpingJacs4)

        val jumpingJacs5 = ExerciseModel(
            6,
            "Jumping Hello",
            R.drawable.img_5,
            false,
            false
        )
        exerciseList.add(jumpingJacs5)

        val jumpingJacs6 = ExerciseModel(
            7,
            "Jumping Kris",
            R.drawable.img_6,
            false,
            false
        )
        exerciseList.add(jumpingJacs6)

        val jumpingJacs7 = ExerciseModel(
            8,
            "Jumping Kumar",
            R.drawable.img_4,
            false,
            false
        )
        exerciseList.add(jumpingJacs7)

        return exerciseList
    }
}