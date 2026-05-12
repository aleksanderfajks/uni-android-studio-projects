package com.example.lab51

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val weightEditText = findViewById<TextInputEditText>(R.id.weightEditText)
        val heightEditText = findViewById<TextInputEditText>(R.id.heightEditText)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)

        calculateButton.setOnClickListener {
            val weightStr = weightEditText.text.toString()
            val heightStr = heightEditText.text.toString()

            if (weightStr.isNotEmpty() && heightStr.isNotEmpty()) {
                val weight = weightStr.toFloat()
                val height = heightStr.toFloat() / 100 // cm to m

                if (height > 0) {
                    val bmi = weight / (height * height)
                    resultTextView.text = getString(R.string.bmi_result, bmi)
                    descriptionTextView.text = getBmiDescription(bmi)
                    
                    val color = getBmiColor(bmi)
                    resultTextView.setTextColor(color)
                    descriptionTextView.setTextColor(color)
                }
            } else {
                resultTextView.text = getString(R.string.enter_values)
                descriptionTextView.text = ""
                resultTextView.setTextColor(ContextCompat.getColor(this, R.color.black))
            }
        }
    }

    private fun getBmiDescription(bmi: Float): String {
        return when {
            bmi < 16 -> getString(R.string.bmi_starvation)
            bmi < 17 -> getString(R.string.bmi_emaciation)
            bmi < 18.5 -> getString(R.string.bmi_underweight)
            bmi < 25 -> getString(R.string.bmi_normal)
            bmi < 30 -> getString(R.string.bmi_overweight)
            bmi < 35 -> getString(R.string.bmi_obesity_1)
            bmi < 40 -> getString(R.string.bmi_obesity_2)
            else -> getString(R.string.bmi_obesity_3)
        }
    }

    private fun getBmiColor(bmi: Float): Int {
        return when {
            bmi < 18.5 -> ContextCompat.getColor(this, R.color.bmi_underweight)
            bmi < 25 -> ContextCompat.getColor(this, R.color.bmi_normal)
            else -> ContextCompat.getColor(this, R.color.bmi_warning)
        }
    }
}