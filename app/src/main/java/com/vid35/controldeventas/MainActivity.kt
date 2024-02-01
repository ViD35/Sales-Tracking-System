package com.vid35.controldeventas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.vid35.controldeventas.database.ClienteDatabase
import com.vid35.controldeventas.misc.ClienteState
import com.vid35.controldeventas.screen.ClienteScreen
import com.vid35.controldeventas.ui.theme.ControlDeVentasTheme

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            ClienteDatabase::class.java,
            "Cliente.db"
        ).build()
    }

    private val viewModel by viewModels<ClienteViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ClienteViewModel(db.dao) as T
                }

            }
        }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /*ControlDeVentasTheme {
                Surface(
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn(
                        Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            TestUserForm()
                        }
                    }
                }
            }*/
            val state by viewModel.state.collectAsState()
            ClienteScreen(state = state, onEvent = viewModel::onEvent)
        }
    }
}

@Composable
fun TestUserForm() {
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
        modifier = Modifier
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

@Composable
fun TestDisplayUserList() {
    Column() {

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ControlDeVentasTheme {

    }
}