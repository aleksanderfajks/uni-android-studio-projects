package com.example.foodapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.foodapp.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obsługa strzałki wstecz w pasku akcji
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "O nas"

        // Domniemana intencja: Zadzwoń
        binding.btnCall.setOnClickListener {
            val phone = "+48123456789"
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
            startActivity(intent)
        }

        // Domniemana intencja: Pokaż na mapie
        binding.btnMap.setOnClickListener {
            val address = "geo:0,0?q=ul. Smaczna 1, Wrocław"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(address))
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
