package com.example.proyectointegrador.data.dto

data class Vehiculo(
    val id: String,
    val marca: String,
    val modelo: String,
    val precioPorDia: Double,
    val disponible: Boolean = true
)
