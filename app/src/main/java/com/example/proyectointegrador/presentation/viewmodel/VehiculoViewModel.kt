package com.example.proyectointegrador.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.proyectointegrador.data.dto.Vehiculo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class VehiculoViewModel : ViewModel() {

    private val _vehiculos = MutableStateFlow<List<Vehiculo>>(
        listOf(
            Vehiculo(UUID.randomUUID().toString(), "Toyota", "Corolla 2024", 45.0),
            Vehiculo(UUID.randomUUID().toString(), "Ford", "Mustang GT", 95.0),
            Vehiculo(UUID.randomUUID().toString(), "Chevrolet", "Tracker SUV", 60.0)
        )
    )
    val vehiculos: StateFlow<List<Vehiculo>> = _vehiculos.asStateFlow()

    fun agregarVehiculo(marca: String, modelo: String, precioPorDia: Double) {
        val nuevoVehiculo = Vehiculo(
            id = UUID.randomUUID().toString(),
            marca = marca,
            modelo = modelo,
            precioPorDia = precioPorDia
        )
        _vehiculos.value = _vehiculos.value + nuevoVehiculo
    }

    fun eliminarVehiculo(id: String) {
        _vehiculos.value = _vehiculos.value.filter { it.id != id }
    }
}
