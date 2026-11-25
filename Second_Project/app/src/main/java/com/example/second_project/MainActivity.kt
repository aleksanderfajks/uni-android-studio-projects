package com.example.second_project

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.second_project.ui.theme.Second_ProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Second_ProjectTheme {
                SensorApp()
            }
        }
    }
}

@Composable
fun SensorApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentRoute == "accel",
                    onClick = { navController.navigate("accel") },
                    icon = { Icon(Icons.Default.Place, contentDescription = null) },
                    label = { Text("Akcelerometr") }
                )
                NavigationBarItem(
                    selected = currentRoute == "light",
                    onClick = { navController.navigate("light") },
                    icon = { Icon(Icons.Default.Info, contentDescription = null) },
                    label = { Text("Światło") }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "accel",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("accel") { AccelerometerScreen() }
            composable("light") { LightSensorScreen() }
        }
    }
}

@Composable
fun AccelerometerScreen() {
    val context = LocalContext.current
    var x by remember { mutableFloatStateOf(0f) }
    var y by remember { mutableFloatStateOf(0f) }

    DisposableEffect(Unit) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    // Odczyt danych z sensora
                    x = it.values[0]
                    y = it.values[1]
                }
            }
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)
        onDispose { sensorManager.unregisterListener(listener) }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Wizualizacja: Poziomica", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))
        
        // Wizualizacja: Poruszająca się kulka
        Canvas(modifier = Modifier.size(250.dp).background(Color.LightGray, CircleShape)) {
            val centerX = size.width / 2
            val centerY = size.height / 2
            // Przesunięcie kulki (x jest odwrócone w sensorze względem ekranu)
            val ballX = centerX - (x * 20)
            val ballY = centerY + (y * 20)
            
            drawCircle(
                color = Color.Blue,
                radius = 30f,
                center = Offset(
                    ballX.coerceIn(30f, size.width - 30f),
                    ballY.coerceIn(30f, size.height - 30f)
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("X: ${"%.2f".format(x)}, Y: ${"%.2f".format(y)}")
    }
}

@Composable
fun LightSensorScreen() {
    val context = LocalContext.current
    var lux by remember { mutableFloatStateOf(0f) }

    DisposableEffect(Unit) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let { lux = it.values[0] }
            }
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)
        onDispose { sensorManager.unregisterListener(listener) }
    }

    // Wizualizacja: Zmiana jasności tła / koloru w zależności od natężenia światła
    val brightness = (lux / 500f).coerceIn(0f, 1f)
    val circleColor = Color(1f, 0.9f, 0f, brightness)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Wizualizacja: Jasność", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))
        
        Surface(
            modifier = Modifier.size(200.dp),
            shape = CircleShape,
            color = circleColor,
            border = androidx.compose.foundation.BorderStroke(2.dp, Color.DarkGray)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text("${lux.toInt()} lx", style = MaterialTheme.typography.titleLarge)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        Text("Zasłoń sensor, aby zobaczyć zmianę.")
    }
}
