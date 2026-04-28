package com.example.lab24

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.inc

class MainActivity : AppCompatActivity() {
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etName = findViewById<EditText>(R.id.etName)
        val btnReadName = findViewById<Button>(R.id.btnReadName)
        val tvCounter = findViewById<TextView>(R.id.tvCounter)
        val btnAdd1 = findViewById<Button>(R.id.btnAdd1)
        val etFirstNumber = findViewById<EditText>(R.id.etFirstNumber)
        val etSecondNumber = findViewById<EditText>(R.id.etSecondNumber)
        val btnMultiply = findViewById<Button>(R.id.btnMultiply)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnReadName.setOnClickListener {
            val name = etName.text.toString()
            if (name.isNotEmpty()) {
                Toast.makeText(this, "Witaj, $name!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Najpierw podaj imię", Toast.LENGTH_SHORT).show()
            }
        }

        btnAdd1.setOnClickListener {
            counter++
            tvCounter.text = "Licznik wciśnięć przycisku: $counter"
        }

        btnMultiply.setOnClickListener {
            val num1Str = etFirstNumber.text.toString()
            val num2Str = etSecondNumber.text.toString()

            if (num1Str.isNotEmpty() && num2Str.isNotEmpty()) {
                try {
                    val num1 = num1Str.toDouble()
                    val num2 = num2Str.toDouble()
                    val result = num1 * num2
                    tvResult.text = result.toString()
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Wprowadź poprawne liczby", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Uzupełnij obie liczby", Toast.LENGTH_SHORT).show()
            }
        }
    }
}