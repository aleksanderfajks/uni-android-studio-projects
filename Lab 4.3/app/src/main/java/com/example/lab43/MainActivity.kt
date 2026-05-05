package com.example.lab43

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pl.droidsonroids.gif.GifDrawable
import pl.droidsonroids.gif.GifImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnRzuc = findViewById<Button>(R.id.btnRzuc)
        val givKostka = findViewById<GifImageView>(R.id.givKostka)
        val ivWynikKostka = findViewById<ImageView>(R.id.ivWynikKostka)
        val tvWynik = findViewById<TextView>(R.id.tvWynik)

        btnRzuc.setOnClickListener {
            ivWynikKostka.visibility = View.GONE
            tvWynik.visibility = View.INVISIBLE
            givKostka.visibility = View.VISIBLE

            try {
                val gifDrawable = GifDrawable(resources, R.drawable.kostkagif)
                givKostka.setImageDrawable(gifDrawable)
                gifDrawable.start()
            } catch (e: Exception) {
                givKostka.setImageResource(R.drawable.kostkagif)
            }
            
            Toast.makeText(this, "Rzuciłeś kostką!", Toast.LENGTH_SHORT).show()

            Handler(Looper.getMainLooper()).postDelayed({
                val wynik = (1..6).random()
                
                val obrazId = when (wynik) {
                    1 -> R.drawable.dice1
                    2 -> R.drawable.dice2
                    3 -> R.drawable.dice3
                    4 -> R.drawable.dice4
                    5 -> R.drawable.dice5
                    6 -> R.drawable.dice6
                    else -> R.drawable.dice1
                }

                givKostka.visibility = View.GONE
                ivWynikKostka.setImageResource(obrazId)
                ivWynikKostka.visibility = View.VISIBLE
                
                val wynikTekst = "Wyrzuciłeś $wynik oczka!"
                tvWynik.text = wynikTekst
                tvWynik.visibility = View.VISIBLE
                
                Toast.makeText(this, wynikTekst, Toast.LENGTH_SHORT).show()
            }, 1500)
        }
    }
}
