package com.example.palette

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.Timer
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btn_start: Button
    private lateinit var btn_refresh: Button
    private lateinit var tv_millisecond: TextView
    private lateinit var tv_second: TextView
    private lateinit var tv_minute: TextView

    var isRunning = false
    var timer : Timer? = null
    var time = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start = findViewById(R.id.btn_start)
        btn_refresh = findViewById(R.id.btn_refresh)
        tv_millisecond = findViewById(R.id.tv_millisecond)
        tv_second = findViewById(R.id.tv_second)
        tv_minute = findViewById(R.id.tv_minute)

        btn_start.setOnClickListener(this)
        btn_refresh.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_start -> {
                if (isRunning) {
                    pause()
                } else {
                    start()
                }
            }

            R.id.btn_refresh -> {
                refresh()
            }
        }
    }

    // 스탑워치 시작
    private fun start() {
        btn_start.text = "일시정지"
        btn_start.setBackgroundColor(getColor(R.color.red))
        isRunning = true

        // 백그라운드 스레드에서만 실행
        timer = timer(period = 10) {
            time++

            val millis = time * 10
            val total_seconds = millis / 1000
            val minute = total_seconds / 60
            val second = total_seconds % 60
            val milli_second = millis % 100

            runOnUiThread {
                if (isRunning) {
                    tv_millisecond.text =
                        if (milli_second < 10) "0${milli_second}" else ".${milli_second}"
                    tv_second.text = if (second < 10) ":0${second}" else ":${second}"
                    tv_minute.text = "${minute}"
                }
            }

        }

    }
    private fun pause() {
        btn_start.text = "시작"
        btn_start.setBackgroundColor(getColor(R.color.blue))

        isRunning = false
        timer?.cancel()
    }
    private fun refresh() {
        timer?.cancel()

        btn_start.text = "시작"
        btn_start.setBackgroundColor(getColor(R.color.blue))
        isRunning = false

        time = 0
        tv_millisecond.text = ".00"
        tv_second.text = ":00"
        tv_minute.text = "00"
    }

}