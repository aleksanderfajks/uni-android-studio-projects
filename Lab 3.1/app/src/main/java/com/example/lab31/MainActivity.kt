package com.example.lab31

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editTextImieINazwisko = findViewById<EditText>(R.id.editTextImieINazwisko)
        val button = findViewById<Button>(R.id.buttonZatwierdz)
        val textViewWynik = findViewById<TextView>(R.id.textViewWynik)

        button.setOnClickListener {
            val imie_i_nazwisko = editTextImieINazwisko.text.toString().trim()

            Log.d(TAG, "Wprowadzone dane: $imie_i_nazwisko")
            textViewWynik.text = "Witaj, $imie_i_nazwisko!"
            Toast.makeText(this, "Witaj, $imie_i_nazwisko!", Toast.LENGTH_LONG).show()
        }
    }

}