package com.vid35.controldeventas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vid35.controldeventas.ui.theme.ControlDeVentasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ControlDeVentasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TestForm("ViD35")
                }
            }
        }
    }
}

@Composable
fun TestForm(name: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    )
    {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Text(
            text = "I hope you don't mind I write some random text to try things out...",
            modifier = modifier
        )
    }
}

@Composable
fun TestUserForm(name: String, modifier: Modifier = Modifier) {
    val subModifier = Modifier.padding(6.dp)
    var nombre by remember {
        mutableStateOf("")
    }
    var apellido by remember {
        mutableStateOf("")
    }
    var telefono by remember {
        mutableStateOf("")
    }
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {
        Text(text = "Nombre", subModifier)
        OutlinedTextField(
            value = nombre,
            onValueChange = { text ->
                nombre = text
            }, subModifier
        )
        Text(text = "Apellido", subModifier)
        OutlinedTextField(
            value = apellido,
            onValueChange = { text ->
                apellido = text
            }, subModifier
        )
        Text(text = "Teléfono", subModifier)
        OutlinedTextField(
            value = telefono,
            onValueChange = { text ->
                telefono = text
            }, subModifier
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Ingresar", subModifier)
            Button(onClick = {

            }, subModifier) {
                Text(text = "Submit")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ControlDeVentasTheme {
        TestUserForm("Maria",
            Modifier
            .fillMaxSize()
            .padding(12.dp)
        )
    }
}