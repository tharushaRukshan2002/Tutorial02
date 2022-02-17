package com.example.tut2

import android.annotation.SuppressLint
import android.content.Intent
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

    //intent for the 2nd view
    private lateinit var activity2Intent: Intent

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        accessingElements()
        createDogs()
        randomImages()//displaying random images when the screen starts.
        val submitBtn: Button = findViewById(R.id.submitBtn)
        questionTxt = findViewById(R.id.questionTxt)
        val nextBtn: Button = findViewById(R.id.nextBtn)
        val finishBtn: Button = findViewById(R.id.finishBtn)

        imageButton[0].setOnClickListener {
           validation(imageButton[0])

        }
        imageButton[1].setOnClickListener {
            validation(imageButton[1])

        }
        imageButton[2].setOnClickListener {
            validation(imageButton[2])

        }
        nextBtn.setOnClickListener {
            var i = 0
            while (i < imageButton.size){
                imageButton[i].isEnabled = true
                i++
            }
            if (dogs.size != dogImagesNumber.size) {
                submitBtn.isEnabled = false
                selectedAnswer = null
                questionTxt.text = "Select the image for dog breed"
                randomImages()
            } else {
                nextBtn.isEnabled = false
            }

        }

//        submitBtn.setOnClickListener {
//            submitBtn.isEnabled = false
//            randomBreed.setTypeface(null, Typeface.ITALIC)
//            questionTxt.text = "Your answer is"
//            when (selectedAnswer) {
//                correctAnswer -> {
//
//                }
//                null -> {
//                    questionTxt.text = "Guess!!!!"
//                }
//                else -> {
//                    randomBreed.text = "WRONG!"
//                    randomBreed.setTextColor((Color.parseColor("#e81e1e")))///red
//                    numWrongAnswers++
//                }
//            }
//        }

        finishBtn.setOnClickListener {
            val correctStr: String = numCorrectAnswers.toString()
            val wrongStr: String = numWrongAnswers.toString()
            activity2Intent = Intent(this, MainActivity2::class.java)
            activity2Intent.putExtra("correct_answers", correctStr)
            activity2Intent.putExtra("wrong_answers", wrongStr)
            startActivity(activity2Intent)
        }


    }

    /**
     * creates 9 dog objects and assassin it to dogs array
     */
    private fun createDogs() {
        dogs.add(Dog(R.drawable.affenpinscher, "Affenpinscher"))
        dogs.add(Dog(R.drawable.afghanhound, "Afghan Hound"))
        dogs.add(Dog(R.drawable.airedaleterrier, "Airedale Terrier"))
        dogs.add(Dog(R.drawable.akita, "Akita"))
        dogs.add(Dog(R.drawable.alaskankleekai, "Alaskan klee kai"))
        dogs.add(Dog(R.drawable.alaskanmalamute, "Alaskan Malamute"))
        dogs.add(Dog(R.drawable.pomeranian, "Pomeranian"))
        dogs.add(Dog(R.drawable.siberianhusky, "Siberian Husky"))
        dogs.add(Dog(R.drawable.yellowlabradorretriever, "Labrador"))

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
    private fun randomImages() {
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

    /**
     * will validate the answer and disable all the buttons.
     * @param button corresponding image button as the param
     */
    @SuppressLint("SetTextI18n")
    private fun validation(button: ImageButton){
        var i = 0
        while (i < imageButton.size){
            imageButton[i].isEnabled = false;
            i++
        }
        if(button == correctAnswer){
            randomBreed.text = "CORRECT!"
            randomBreed.setTextColor((Color.parseColor("#0af531")))//green
            numCorrectAnswers++
        }else{
            randomBreed.text = "WRONG!"
            randomBreed.setTextColor((Color.parseColor("#e81e1e")))///red
            numWrongAnswers++
        }
    }


}