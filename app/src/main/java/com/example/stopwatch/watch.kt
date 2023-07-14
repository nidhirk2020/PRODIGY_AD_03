package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import java.util.Locale

class watch : AppCompatActivity() {
    var running=false
    var wasRunning=false
    var second=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch)

        if(savedInstanceState!=null){
            second=savedInstanceState
                .getInt("seconds")
            running=savedInstanceState
                .getBoolean("running")
            wasRunning=savedInstanceState
                .getBoolean("wasRunning")
        }
        runTimer()
    }

    private fun runTimer() {
        val myTv=findViewById<TextView>(R.id.tv)
        val handler = Handler()
        handler.post(object :Runnable{
            override fun run() {
                val hours=second/3600
                val minute=second % 3600/ 60
                val secs= second % 60
                val time=String.format(Locale.getDefault(), format = "%d:%02d:%02d",hours,minute,secs)
                myTv.text=time
                if(running){
                    second++
                }
                handler.postDelayed(this,1000)

            }
        })

    }

    override fun onPause() {
        super.onPause()
        wasRunning=running
        running=false
    }

    override fun onResume() {
        super.onResume()
        if(wasRunning){
            running=true

        }
    }

    fun stop(view: View) {
        running=false
    }
    fun start(view: View) {
        running=true

    }
    fun reset(view: View) {
        running=false
        second=0
    }
}