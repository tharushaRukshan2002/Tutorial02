package com.example.tut2

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView


import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //dogs will be stored in this array
    private val dogs = mutableListOf<Dog>()

    //Image buttons will be stored in this
    private val imageButton = mutableListOf<ImageButton>()

    //dog breed text will be shown in this
    private lateinit var randomBreed: TextView

    //to keep the generated numbers and preventing the same number repeating twice
    private val dogImagesNumber = mutableListOf<Int>()

    //correct answer
    private lateinit var correctAnswer: ImageButton

    //question txt
    private lateinit var questionTxt: TextView

    //selected answer setting null to make sure answer is chosen once
    private var selectedAnswer: ImageButton? = null

    //number of correct answers
    private var numCorrectAnswers: Int = 0

    //number of wrong answers
    private var numWrongAnswers: Int = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        accessingElements()
        createDogs()
        randomImages()
        val submitBtn: Button = findViewById(R.id.submitBtn)
        questionTxt = findViewById(R.id.questionTxt)
        val nextBtn: Button = findViewById(R.id.nextBtn)


        imageButton[0].setOnClickListener {
            if (selectedAnswer == null) {
                selectedAnswer = imageButton[0]
            }

        }
        imageButton[1].setOnClickListener {
            if (selectedAnswer == null) {
                selectedAnswer = imageButton[1]
            }

        }
        imageButton[2].setOnClickListener {
            if (selectedAnswer == null) {
                selectedAnswer = imageButton[2]
            }

        }
        nextBtn.setOnClickListener {
            selectedAnswer = null
            questionTxt.text = "Select the image for dog breed"
            randomImages()
        }



        submitBtn.setOnClickListener {

            randomBreed.setTypeface(null, Typeface.ITALIC)
            questionTxt.text = "Your answer is"
            when (selectedAnswer) {
                correctAnswer -> {
                    randomBreed.text = "CORRECT!"
                    randomBreed.setTextColor((Color.parseColor("#0af531")))//green
                    numCorrectAnswers += 1
                }
                null -> {
                    questionTxt.text = "Guess!!!!"
                }
                else -> {
                    randomBreed.text = "WRONG!"
                    randomBreed.setTextColor((Color.parseColor("#e81e1e")))///red
                    numWrongAnswers += 1
                }
            }

        }

    }

    /**
     * creates six dog objects and assassin it to dogs array
     */
    private fun createDogs() {
        dogs.add(Dog(R.drawable.affenpinscher, "Affenpinscher"))
        dogs.add(Dog(R.drawable.afghanhound, "Afghan Hound"))
        dogs.add(Dog(R.drawable.airedaleterrier, "Airedale Terrier"))
        dogs.add(Dog(R.drawable.akita, "Akita"))
        dogs.add(Dog(R.drawable.alaskankleekai, "Alaskan klee kai"))
        dogs.add(Dog(R.drawable.alaskanmalamute, "Alaskan Malamute"))

    }

    /**
     * accessing the fields
     */
    private fun accessingElements() {
        imageButton.add(findViewById(R.id.imageButton))
        imageButton.add(findViewById(R.id.imageButton2))
        imageButton.add(findViewById(R.id.imageButton3))
        randomBreed = findViewById(R.id.randomBreedTxt)

    }

    /**
     * This will put random images into random image buttons. and the local array will keep track
     * of the numbers.
     */
    @SuppressLint("SetTextI18n")
    private fun randomImages() {

        if (dogs.size != dogImagesNumber.size) {

            val imageFrameNum = mutableListOf<Int>()
            var i = 0
            while (i < imageButton.size) {
                val frameNum =
                    (0 until imageButton.size).random() //this number selects the ImageButton from the array
                val dogPic = (0 until dogs.size).random() //random number to take dogs array dog

                if (frameNum !in imageFrameNum && dogPic !in dogImagesNumber) {

                    if (i == 0) {
                        imageButton[frameNum].setImageResource(dogs[dogPic].image)// setting the image
                        imageFrameNum.add(frameNum)
                        correctAnswer = imageButton[frameNum]
                        dogImagesNumber.add(dogPic)
                        randomBreed.text = dogs[dogPic].name
                        randomBreed.setTextColor((Color.parseColor("#FF5252")))
                        ++i
                    } else {
                        imageButton[frameNum].setImageResource(dogs[dogPic].image)
                        imageFrameNum.add(frameNum)
                        dogImagesNumber.add(dogPic)
                        ++i
                    }

                }
            }
        }

    }


}