package com.example.tut2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val correctTxt: TextView = findViewById(R.id.correctTxt)
        val correctNum = intent.getStringExtra("correct_answers")
        correctTxt.text = "$correctNum"

        val wrongTxt: TextView = findViewById(R.id.wrongTxt)
        val wrongNum = intent.getStringExtra("wrong_answers")
        wrongTxt.text = "$wrongNum"
    }
}



