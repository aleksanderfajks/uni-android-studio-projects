package com.example.lab71

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val btnNavigate = findViewById<Button>(R.id.btnNavigate)

        btnNavigate.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId
            val intent = when (selectedId) {
                R.id.radioFirst -> Intent(this, FirstActivity::class.java)
                R.id.radioSecond -> Intent(this, SecondActivity::class.java)
                else -> null
            }
            intent?.let { startActivity(it) }
        }
    }
}