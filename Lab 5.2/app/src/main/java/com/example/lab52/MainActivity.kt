package com.example.lab52

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val density = v.context.resources.displayMetrics.density
            val basePadding = (24 * density).toInt()
            
            v.setPadding(
                systemBars.left + basePadding,
                systemBars.top + basePadding,
                systemBars.right + basePadding,
                systemBars.bottom + basePadding
            )
            insets
        }

        val etGrossSalary = findViewById<TextInputEditText>(R.id.etGrossSalary)
        val etEmployeePercent = findViewById<TextInputEditText>(R.id.etEmployeePercent)
        val etEmployerPercent = findViewById<TextInputEditText>(R.id.etEmployerPercent)
        val etYearsToRetirement = findViewById<TextInputEditText>(R.id.etYearsToRetirement)
        val etReturnRate = findViewById<TextInputEditText>(R.id.etReturnRate)
        val etRetirementYears = findViewById<TextInputEditText>(R.id.etRetirementYears)
        val cbStateTopUp = findViewById<CheckBox>(R.id.cbStateTopUp)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val tvResultTotal = findViewById<TextView>(R.id.tvResultTotal)
        val tvResultMonthly = findViewById<TextView>(R.id.tvResultMonthly)

        btnCalculate.setOnClickListener {
            val grossSalary = etGrossSalary.text.toString().toDoubleOrNull() ?: 0.0
            val employeePercent = etEmployeePercent.text.toString().toDoubleOrNull() ?: 0.0
            val employerPercent = etEmployerPercent.text.toString().toDoubleOrNull() ?: 0.0
            val yearsToRetirement = etYearsToRetirement.text.toString().toIntOrNull() ?: 0
            val returnRate = etReturnRate.text.toString().toDoubleOrNull() ?: 0.0
            val retirementYears = etRetirementYears.text.toString().toIntOrNull() ?: 0
            val includeStateTopUp = cbStateTopUp.isChecked

            val monthlyEmployeeContribution = grossSalary * (employeePercent / 100.0)
            val monthlyEmployerContribution = grossSalary * (employerPercent / 100.0)
            val monthlyTotalContribution = monthlyEmployeeContribution + monthlyEmployerContribution
            
            val monthlyReturnRate = (returnRate / 100.0) / 12.0
            val totalMonths = yearsToRetirement * 12
            
            var totalAccumulated = 0.0
            
            for (month in 1..totalMonths) {
                totalAccumulated = (totalAccumulated + monthlyTotalContribution) * (1 + monthlyReturnRate)
                
                // Dopłata roczna (240 PLN) dodawana na koniec każdego roku
                if (includeStateTopUp && month % 12 == 0) {
                    totalAccumulated += 240.0
                }
            }
            
            val monthlyRetirementPayout = if (retirementYears > 0) {
                totalAccumulated / (retirementYears * 12)
            } else {
                0.0
            }

            tvResultTotal.text = getString(R.string.result_total, totalAccumulated)
            tvResultMonthly.text = getString(R.string.result_monthly, monthlyRetirementPayout)
        }
    }
}
