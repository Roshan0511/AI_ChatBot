package com.roshan.aichatbot.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.roshan.aichatbot.R
import com.roshan.aichatbot.databinding.ActivityMainBinding
import com.roshan.aichatbot.fragments.AskGenderFragment
import com.roshan.aichatbot.fragments.SplashScreenFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val fragment = SplashScreenFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainFrame, fragment)
        transaction.commit()
    }
}