package com.example.lab41

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab41.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnLosujCzynnosc.setOnClickListener {
            Toast.makeText(
                this,
                "Kliknąłeś wybór dzisiejszej czynności!\nTrwa losowanie czynności...",
                Toast.LENGTH_SHORT
            ).show()

            binding.tvWynikCzynnosci.text = "Trwa losowanie..."

            Handler(Looper.getMainLooper()).postDelayed({
                val wynikLosowania = (1..6).random() // Losowanie

                val wylosowanaCzynnosc = when (wynikLosowania) {
                    1 -> "spacer!"
                    2 -> "grę w piłkę nożną!"
                    3 -> "grę w koszykówkę!"
                    4 -> "jazdę na rowerze!"
                    5 -> "grę na gitarze!"
                    6 -> "grę w szachy!"
                    else -> "odpoczynek!"
                }

                binding.tvWynikCzynnosci.text = "Wybrałeś $wylosowanaCzynnosc"
            }, 2000)
        }
    }
}