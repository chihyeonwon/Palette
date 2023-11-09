package com.example.palette

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.minute1).setOnClickListener {
            val intent = Intent(this, minute1Activity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.minute3).setOnClickListener {
            val intent = Intent(this, minute3Activity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.minute5).setOnClickListener {
            val intent = Intent(this, minute5Activity::class.java)
            startActivity(intent)
        }
    }
}