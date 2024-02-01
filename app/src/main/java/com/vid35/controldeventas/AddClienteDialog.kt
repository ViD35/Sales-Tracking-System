package com.vid35.controldeventas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vid35.controldeventas.event.ClienteEvent
import com.vid35.controldeventas.misc.ClienteState

@Composable
fun AddClienteDialog(
    state: ClienteState,
    onEvent: (ClienteEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(ClienteEvent.HideDialog) },
        title = { Text(text = "Agregar cliente")},
        text = {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Alias")
                OutlinedTextField(
                    value = state.aliasCliente,
                    onValueChange = {
                        onEvent(ClienteEvent.SetAliasCliente(it))
                    })
                Text(text = "Nombre")
                OutlinedTextField(
                    value = state.nombreCliente,
                    onValueChange = {
                        onEvent(ClienteEvent.SetNombreCliente(it))
                    })
                Text(text = "Apellido")
                OutlinedTextField(
                    value = state.apellidoCliente,
                    onValueChange = {
                        onEvent(ClienteEvent.SetApellidoCliente(it))
                    })
                Text(text = "Teléfono")
                OutlinedTextField(
                    value = state.telefonoCliente,
                    onValueChange = {
                        onEvent(ClienteEvent.SetTelefonoCliente(it))
                    })
            }
        },
        confirmButton = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {
                    onEvent(ClienteEvent.SaveCliente)
                }) {
                    Text(text = "Submit")
                }
            }
        })
}