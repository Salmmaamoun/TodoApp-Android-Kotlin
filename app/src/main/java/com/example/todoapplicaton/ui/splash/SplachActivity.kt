package com.example.todoapplicaton.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.todoapplicaton.R
import com.example.todoapplicaton.ui.home.MainActivity

class SplachActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach)
        Handler(Looper.getMainLooper()).postDelayed(object :Runnable{
            override fun run() {
                val intent=Intent(this@SplachActivity, MainActivity::class.java)
                startActivity(intent)
            }
        },3000)
    }
}


