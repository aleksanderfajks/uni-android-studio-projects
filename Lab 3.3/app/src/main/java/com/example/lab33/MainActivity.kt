package com.example.lab33

import android.os.Bundle
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupGate(R.id.tbAnd1,  R.id.tbAnd2,  R.id.tbAndWynik)  { a, b -> a && b }
        setupGate(R.id.tbNand1, R.id.tbNand2, R.id.tbNandWynik) { a, b -> !(a && b) }
        setupGate(R.id.tbOr1,   R.id.tbOr2,   R.id.tbOrWynik)   { a, b -> a || b }
        setupGate(R.id.tbNor1,  R.id.tbNor2,  R.id.tbNorWynik)  { a, b -> !(a || b) }
        setupGate(R.id.tbXor1,  R.id.tbXor2,  R.id.tbXorWynik)  { a, b -> a xor b }
        setupGate(R.id.tbXnor1, R.id.tbXnor2, R.id.tbXnorWynik) { a, b -> !(a xor b) }
    }

    private fun setupGate(
        idA: Int, idB: Int, idWynik: Int,
        logic: (Boolean, Boolean) -> Boolean) {
        val tbA     = findViewById<ToggleButton>(idA)
        val tbB     = findViewById<ToggleButton>(idB)
        val tbWynik = findViewById<ToggleButton>(idWynik)

        tbWynik.isClickable = false

        fun update() {
            tbWynik.isChecked = logic(tbA.isChecked, tbB.isChecked)
        }

        tbA.setOnCheckedChangeListener { _, _ -> update() }
        tbB.setOnCheckedChangeListener { _, _ -> update() }
        update()
    }
}