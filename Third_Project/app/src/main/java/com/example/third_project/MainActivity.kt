package com.example.third_project

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = Firebase.database("https://third-project-91544-default-rtdb.europe-west1.firebasedatabase.app").reference
        val ledsRef = database.child("leds")
        val modeRef = database.child("mode")

        val pinNames = listOf("33", "32", "27", "26", "25", "21", "19", "18", "13", "4")

        setContent {
            var ledStates by remember { mutableStateOf(List(10) { false }) }
            var currentMode by remember { mutableIntStateOf(0) }

            LaunchedEffect(Unit) {
                ledsRef.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val list = mutableListOf<Boolean>()
                        snapshot.children.forEach { list.add(it.value as? Boolean ?: false) }
                        if (list.size == 10) ledStates = list
                    }
                    override fun onCancelled(error: DatabaseError) {}
                })
                modeRef.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        currentMode = (snapshot.value as? Long)?.toInt() ?: 0
                    }
                    override fun onCancelled(error: DatabaseError) {}
                })
            }

            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("STEROWANIE LED ESP32", style = MaterialTheme.typography.headlineSmall)
                    
                    Text("Tryb: ${if (currentMode == 0) "Manualny" else "Animacja $currentMode"}", 
                        modifier = Modifier.padding(8.dp), color = Color.Gray)

                    // Siatka przycisków dla poszczególnych diod
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(5),
                        modifier = Modifier.weight(1f).padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(10) { index ->
                            Button(
                                onClick = {
                                    val newList = ledStates.toMutableList()
                                    newList[index] = !newList[index]
                                    ledsRef.setValue(newList)
                                    modeRef.setValue(0) // Powrót do manualnego przy kliknięciu
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (ledStates[index]) Color.Green else Color.DarkGray
                                ),
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(pinNames[index], style = MaterialTheme.typography.labelSmall)
                            }
                        }
                    }

                    // Przyciski trybów
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ModeButton("OFF", 0, currentMode, modeRef)
                        ModeButton("MIG", 1, currentMode, modeRef)
                        ModeButton("FALA", 2, currentMode, modeRef)
                        ModeButton("KITT", 3, currentMode, modeRef)
                    }
                }
            }
        }
    }

    @Composable
    fun ModeButton(text: String, mode: Int, currentMode: Int, ref: com.google.firebase.database.DatabaseReference) {
        Button(
            onClick = { ref.setValue(mode) },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (currentMode == mode) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
            )
        ) {
            Text(text)
        }
    }
}
