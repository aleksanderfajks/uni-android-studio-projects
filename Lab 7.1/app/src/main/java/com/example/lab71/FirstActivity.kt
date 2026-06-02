package com.example.lab71

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}