package com.example.first_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.first_project.ui.theme.First_ProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            First_ProjectTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "screen1") {
        // Ekran 1: Wprowadzanie danych
        composable("screen1") {
            FirstScreen(onNavigateToSecond = { name ->
                navController.navigate("screen2/$name")
            })
        }

        // Ekran 2: Powitanie (odbiera parametr 'name')
        composable(
            route = "screen2/{userName}",
            arguments = listOf(navArgument("userName") { type = NavType.StringType })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("userName") ?: "Nieznajomy"
            SecondScreen(
                name = name,
                onNavigateToThird = { navController.navigate("screen3") },
                onBack = { navController.popBackStack() }
            )
        }

        // Ekran 3: Koniec / Informacje
        composable("screen3") {
            ThirdScreen(onRestart = {
                navController.navigate("screen1") {
                    popUpTo("screen1") { inclusive = true }
                }
            })
        }
    }
}

@Composable
fun FirstScreen(onNavigateToSecond: (String) -> Unit) {
    var nameInput by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Ekran 1: Podaj imię", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = nameInput,
                onValueChange = { nameInput = it },
                label = { Text("Twoje imię") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (nameInput.isNotBlank()) {
                    onNavigateToSecond(nameInput)
                }
            }) {
                Text("Dalej do Ekranu 2")
            }
        }
    }
}

@Composable
fun SecondScreen(name: String, onNavigateToThird: () -> Unit, onBack: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Ekran 2: Witaj!", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Cześć $name!", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onNavigateToThird) {
                Text("Przejdź do Ekranu 3")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onBack) {
                Text("Wróć")
            }
        }
    }
}

@Composable
fun ThirdScreen(onRestart: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Ekran 3: Koniec", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "To jest ostatni ekran aplikacji.")
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onRestart) {
                Text("Zacznij od nowa")
            }
        }
    }
}
