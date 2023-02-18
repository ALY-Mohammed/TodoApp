package com.example.todoapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed(object :Runnable{
            override fun run() {
              val intent = Intent (this@SplashActivity,HomeTodoApp::class.java)
                startActivity(intent)
            }
        },500)

    }
}