package com.example.proyectointegrador.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectointegrador.data.dto.Vehiculo
import com.example.proyectointegrador.presentation.components.VehiculoCard
import com.example.proyectointegrador.presentation.viewmodel.VehiculoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehiculoScreen(
    viewModel: VehiculoViewModel,
    onNavigateBack: () -> Unit,
    onVerDetalle: (Vehiculo) -> Unit
) {
    val vehiculos by viewModel.vehiculos.collectAsState()

    var marca by remember { mutableStateOf("") }
    var modelo by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestión de Vehículos") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Registrar Nuevo Vehículo", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = marca,
                onValueChange = { marca = it },
                label = { Text("Marca (ej. Toyota, Ford)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = modelo,
                onValueChange = { modelo = it },
                label = { Text("Modelo (ej. Corolla 2024)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = precio,
                onValueChange = { precio = it },
                label = { Text("Precio por Día ($)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val precioNum = precio.toDoubleOrNull()
                    if (marca.isNotBlank() && modelo.isNotBlank() && precioNum != null) {
                        viewModel.agregarVehiculo(marca, modelo, precioNum)
                        marca = ""
                        modelo = ""
                        precio = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar Vehículo")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Catálogo de Vehículos", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            if (vehiculos.isEmpty()) {
                Text("No hay vehículos registrados.", color = MaterialTheme.colorScheme.onSurfaceVariant)
            } else {
                LazyColumn {
                    items(vehiculos) { vehiculo ->
                        VehiculoCard(
                            vehiculo = vehiculo,
                            onVerDetalle = { onVerDetalle(vehiculo) },
                            onDelete = { viewModel.eliminarVehiculo(vehiculo.id) }
                        )
                    }
                }
            }
        }
    }
}
