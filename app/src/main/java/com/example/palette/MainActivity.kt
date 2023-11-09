package com.example.palette

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var countdown_timer: CountDownTimer? = null
    private var time_in_milliseconds: Long = 300000
    private var is_running = false

    private lateinit var start_btn : Button
    private lateinit var reset_btn : Button

    private lateinit var tv_millisecond : TextView
    private lateinit var tv_second : TextView
    private lateinit var tv_minute : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_minute = findViewById(R.id.tv_minute)
        tv_second = findViewById(R.id.tv_second)
        tv_millisecond = findViewById(R.id.tv_millisecond)

        set_minute_5()

        start_btn = findViewById(R.id.btn_start)

        reset_btn = findViewById(R.id.btn_reset)


        start_btn.setOnClickListener {
            if (is_running) {
                pause_timer()
            } else {
                start_timer()
            }
        }

        findViewById<Button>(R.id.btn_reset).setOnClickListener {
            reset_timer()
        }
    }

    private fun start_timer() {
        countdown_timer = object : CountDownTimer(time_in_milliseconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                time_in_milliseconds = millisUntilFinished
                update_timer_text()
            }

            override fun onFinish() {
                is_running = false
                set_minute_5()
                start_btn.text = "시작"
                start_btn.setBackgroundColor(getColor(R.color.blue))
            }
        }.start()

        is_running = true
        start_btn.text = "일시정지"
        start_btn.setBackgroundColor(getColor(R.color.red))
    }

    private fun pause_timer() {
        countdown_timer?.cancel()
        is_running = false
        start_btn.text = "시작"
        start_btn.setBackgroundColor(getColor(R.color.blue))
    }

    private fun reset_timer() {
        countdown_timer?.cancel()
        is_running = false
        time_in_milliseconds = 300000
        set_minute_5()
        start_btn.text = "시작"
        start_btn.setBackgroundColor(getColor(R.color.blue))
    }

    private fun update_timer_text() {
        val minutes = (time_in_milliseconds / 1000) / 60
        val seconds = (time_in_milliseconds / 1000) % 60
        val milliseconds = (time_in_milliseconds % 1000) / 10

        tv_minute.text = if (minutes < 10) "0$minutes" else "$minutes"
        tv_second.text = if (seconds < 10) ":0$seconds" else ":$seconds"
        tv_millisecond.text = if (milliseconds < 10) ".0$milliseconds" else ".$milliseconds"
    }

    private fun set_minute_5() {
        tv_minute.text = "05"
        tv_second.text = ":00"
        tv_millisecond.text = ".00"
    }
}