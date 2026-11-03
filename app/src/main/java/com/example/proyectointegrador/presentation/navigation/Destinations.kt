package com.example.proyectointegrador.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object Home : Destination

    @Serializable
    data object Vehiculos : Destination

    @Serializable
    data object Clientes : Destination

    @Serializable
    data class DetalleVehiculo(
        val id: String,
        val marca: String,
        val modelo: String,
        val precioPorDia: Double
    ) : Destination
}
