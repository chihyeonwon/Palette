package com.example.palette

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class minute3Activity : AppCompatActivity() {
    private var countdown_timer: CountDownTimer? = null
    private var time_in_milliseconds: Long = 180000
    private var is_running = false

    private lateinit var start_btn : Button
    private lateinit var reset_btn : Button

    private lateinit var tv_second : TextView
    private lateinit var tv_minute : TextView

    private val TAG = minute3Activity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_minute3)

        tv_minute = findViewById(R.id.tv_minute3)
        tv_second = findViewById(R.id.tv_second3)

        set_minute_3()

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

        if(tv_minute.text == "00" && tv_second.text==":00") {
            // 시스템 소리
            /* // 소리 얻기
             val sound : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

             // 소리 담기
             val ringtone = RingtoneManager.getRingtone(applicationContext, sound)

             // 실행
             ringtone.play()*/

            // 사용자 정의

            // 소리 얻기
            val player: MediaPlayer = MediaPlayer.create(this, R.raw.tiny_button_push_sound)

            // 실행
            player.start()
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
                set_minute_3()
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
        time_in_milliseconds = 180000
        set_minute_3()
        start_btn.text = "시작"
        start_btn.setBackgroundColor(getColor(R.color.blue))
    }

    private fun update_timer_text() {
        val minutes = (time_in_milliseconds / 1000) / 60
        val seconds = (time_in_milliseconds / 1000) % 60
        val milliseconds = (time_in_milliseconds % 1000) / 10

        tv_minute.text = if (minutes < 10) "0$minutes" else "$minutes"
        tv_second.text = if (seconds < 10) ":0$seconds" else ":$seconds"
    }

    private fun set_minute_3() {
        tv_minute.text = "03"
        tv_second.text = ":00"
    }
}