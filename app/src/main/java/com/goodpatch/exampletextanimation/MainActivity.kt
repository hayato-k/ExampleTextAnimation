package com.goodpatch.exampletextanimation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textAnimation(0, 100, 600, 10)
    }

    /**
     * start: アニメーション開始時の数値
     * end: アニメーション終了時の数値
     * duration: アニメーション表示期間
     * period: 更新時間
     */
    private fun textAnimation(start: Int, end: Int, duration: Int, period: Int) {
        val blockValue = (end - start) * period / duration
        val timer = Timer()
        val handler = Handler(Looper.getMainLooper())
        val animationText: TextView = this.findViewById(R.id.animationText)
        timer.schedule(object : TimerTask() {
            var value = 0
            override fun run() = if (value < end) {
                handler.post {
                    animationText.text = "$value %"
                }
                value += blockValue
            } else {
                value = end
                timer.cancel()
            }
        }, 0, period.toLong())
    }
}
