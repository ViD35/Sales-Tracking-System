package com.vid35.controldeventas.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vid35.controldeventas.AddClienteDialog
import com.vid35.controldeventas.ClienteViewModel
import com.vid35.controldeventas.TestDisplayUserList
import com.vid35.controldeventas.entity.Cliente
import com.vid35.controldeventas.event.ClienteEvent
import com.vid35.controldeventas.misc.ClienteSortType
import com.vid35.controldeventas.misc.ClienteState
import com.vid35.controldeventas.ui.theme.ControlDeVentasTheme

@Composable
fun ClienteScreen(
    state: ClienteState,
    onEvent: (ClienteEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(ClienteEvent.ShowDialog)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agrega cliente"
                )
            }
        }
    ) { padding ->
        if (state.isAddingCliente) {
            AddClienteDialog(state = state, onEvent = onEvent)
        }
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ClienteSortType.values().forEach { clienteSortType ->
                        Row(
                            modifier = Modifier
                                .clickable {
                                    onEvent(ClienteEvent.SortClientes(clienteSortType))
                                },
                            verticalAlignment = CenterVertically
                        ) {
                            RadioButton(
                                selected = state.sortType == clienteSortType,
                                onClick = { 
                                    onEvent(ClienteEvent.SortClientes(clienteSortType))
                                }
                            )
                            Text(text = clienteSortType.name)
                        }
                    }
                }
            }
            items(state.clientes) {cliente ->
                TestUserCard(cliente = cliente, onEvent = onEvent)
            }
        }

    }
}

@Composable
fun TestUserCard(cliente: Cliente, onEvent: (ClienteEvent) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            cliente.aliasCliente?.let {
                Text(
                    text = it,
                    fontSize = 20.sp)
            }
            cliente.telefonoCliente?.let {
                Text(text = it,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
        IconButton(onClick = {
            onEvent(ClienteEvent.DeleteCliente(cliente))
         }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete cliente")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ControlDeVentasTheme {
    }
}