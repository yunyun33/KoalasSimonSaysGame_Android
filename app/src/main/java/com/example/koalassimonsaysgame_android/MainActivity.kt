package com.example.koalassimonsaysgame_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transitToPlayGamePage()

    }

    private fun transitToPlayGamePage() {
        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener() {
            val intent = Intent(this, PlayGame::class.java)
            startActivity(intent)
        }
    }
}