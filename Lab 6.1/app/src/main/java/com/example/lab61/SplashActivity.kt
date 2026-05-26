package com.example.lab61

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {

    private val TAG = "SplashActivityLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showLifecycleEvent("onCreate")
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.splashLogo)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2500)
    }

    override fun onStart() {
        super.onStart()
        showLifecycleEvent("onStart")
    }

    override fun onResume() {
        super.onResume()
        showLifecycleEvent("onResume")
    }

    override fun onPause() {
        super.onPause()
        showLifecycleEvent("onPause")
    }

    override fun onStop() {
        super.onStop()
        showLifecycleEvent("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        showLifecycleEvent("onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        showLifecycleEvent("onRestart")
    }

    private fun showLifecycleEvent(event: String) {
        Log.d(TAG, event)
        Toast.makeText(this, "SplashActivity: $event", Toast.LENGTH_SHORT).show()
    }
}
