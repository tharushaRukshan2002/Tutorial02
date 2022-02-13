package com.example.tut2

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
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

    private lateinit var correctAnswer: ImageButton

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        accessingElements()
        createDogs()
        randomImages()

        imageButton[0].setOnClickListener {
            checkAnswer(imageButton[0])
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
    private fun randomImages() {

        if(dogs.size != dogImagesNumber.size) {
            val imageFrameNum = mutableListOf<Int>()
            var i = 0
            while (i < imageButton.size) {
                val frameNum = (0 until imageButton.size).random() //this number selects the ImageButton from the array
                val dogPic = (0 until dogs.size).random() //random number to take dogs array dog

                if (frameNum !in imageFrameNum && dogPic !in dogImagesNumber) {
                    if (i == 0) {
                        imageButton[frameNum].setImageResource(dogs[dogPic].image)// setting the image
                        imageFrameNum.add(frameNum)
                        correctAnswer = imageButton[frameNum]
                        dogImagesNumber.add(dogPic)
                        randomBreed.text = dogs[dogPic].name
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

    /**
     * check whether the button is the correct match or not
     * @param imageButton: taking image btn as a input.
     */
    private fun checkAnswer(imageButton: ImageButton) {
        if (imageButton == correctAnswer) {
            alert("correct")
        } else {
            alert("wrong")
        }

    }

    private fun alert(massage: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose breed game")
        builder.setMessage(massage)
        builder.show()
    }
}