package com.example.proyectointegrador.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyectointegrador.data.dto.Cliente
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class ClienteViewModel : ViewModel() {

    private val _clientes = MutableStateFlow<List<Cliente>>(
        listOf(
            Cliente(UUID.randomUUID().toString(), "Carlos Mendoza", "1020304050", "3001234567"),
            Cliente(UUID.randomUUID().toString(), "Ana María Gómez", "1098765432", "3159876543")
        )
    )
    val clientes: StateFlow<List<Cliente>> = _clientes.asStateFlow()

    fun agregarCliente(nombre: String, documento: String, telefono: String) {
        val nuevoCliente = Cliente(
            id = UUID.randomUUID().toString(),
            nombre = nombre,
            documento = documento,
            telefono = telefono
        )
        _clientes.value = _clientes.value + nuevoCliente
    }

    fun eliminarCliente(id: String) {
        _clientes.value = _clientes.value.filter { it.id != id }
    }
}
