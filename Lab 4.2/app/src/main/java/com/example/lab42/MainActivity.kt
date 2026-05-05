package com.example.lab42

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val mainView = findViewById<android.view.View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnRzuc = findViewById<Button>(R.id.btnRzucMonetami)
        val tvWynik = findViewById<TextView>(R.id.tvWynikRzutu)
        val ivMoneta1 = findViewById<ImageView>(R.id.ivMoneta1)
        val ivMoneta2 = findViewById<ImageView>(R.id.ivMoneta2)

        btnRzuc.setOnClickListener {
            btnRzuc.isEnabled = false
            tvWynik.text = "Trwa rzut..."
            Toast.makeText(this, "Rzucamy!", Toast.LENGTH_SHORT).show()

            val moneta1 = (0..1).random() // 0 - Orzeł, 1 - Reszka
            val moneta2 = (0..1).random()

            val nazwa1 = if (moneta1 == 0) "Orzeł" else "Reszka"
            val nazwa2 = if (moneta2 == 0) "Orzeł" else "Reszka"

            val obraz1 = if (moneta1 == 0) R.drawable.orzel else R.drawable.reszka
            val obraz2 = if (moneta2 == 0) R.drawable.orzel else R.drawable.reszka

            var zakonczoneAnimacje = 0

            fun sprawdzKoniec() {
                zakonczoneAnimacje++
                if (zakonczoneAnimacje == 2) {
                    tvWynik.text = "Wynik: $nazwa1 i $nazwa2"
                    btnRzuc.isEnabled = true
                }
            }

            animujMonete(ivMoneta1, obraz1, 10, ::sprawdzKoniec)
            animujMonete(ivMoneta2, obraz2, 10, ::sprawdzKoniec)
        }
    }

    private fun animujMonete(imageView: ImageView, docelowyObraz: Int, powtorzenia: Int, onFinish: () -> Unit) {
        imageView.animate().apply {
            duration = 100
            rotationXBy(180f)
        }.withEndAction {
            if (powtorzenia > 0) {
                val tymczasowyObraz = if ((0..1).random() == 0) R.drawable.orzel else R.drawable.reszka
                imageView.setImageResource(tymczasowyObraz)
                animujMonete(imageView, docelowyObraz, powtorzenia - 1, onFinish)
            } else {
                imageView.setImageResource(docelowyObraz)
                imageView.rotationX = 0f
                onFinish()
            }
        }.start()
    }
}