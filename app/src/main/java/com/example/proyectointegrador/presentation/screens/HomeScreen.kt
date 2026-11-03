package com.example.proyectointegrador.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectointegrador.data.dto.Vehiculo
import com.example.proyectointegrador.presentation.components.StatCard
import com.example.proyectointegrador.presentation.components.VehiculoCard
import com.example.proyectointegrador.presentation.viewmodel.ClienteViewModel
import com.example.proyectointegrador.presentation.viewmodel.VehiculoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    vehiculoViewModel: VehiculoViewModel,
    clienteViewModel: ClienteViewModel,
    onNavigateToVehiculos: () -> Unit,
    onNavigateToClientes: () -> Unit,
    onNavigateToDetalle: (Vehiculo) -> Unit
) {
    val vehiculos by vehiculoViewModel.vehiculos.collectAsState()
    val clientes by clienteViewModel.clientes.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AutoRent - Panel Principal") },
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
            Text(
                text = "Bienvenido a AutoRent",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Sistema de Gestión de Alquiler de Vehículos",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    title = "Vehículos",
                    count = vehiculos.size,
                    icon = Icons.Default.DirectionsCar,
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    iconTint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.weight(1f)
                )

                StatCard(
                    title = "Clientes",
                    count = clientes.size,
                    icon = Icons.Default.People,
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    iconTint = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("Accesos Rápidos", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onNavigateToVehiculos,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.DirectionsCar, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Gestionar Vehículos")
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(
                onClick = onNavigateToClientes,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.People, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Gestionar Clientes")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("Vehículos Disponibles", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            if (vehiculos.isEmpty()) {
                Text("No hay vehículos registrados.", color = MaterialTheme.colorScheme.onSurfaceVariant)
            } else {
                LazyColumn {
                    items(vehiculos) { vehiculo ->
                        VehiculoCard(
                            vehiculo = vehiculo,
                            onVerDetalle = { onNavigateToDetalle(vehiculo) }
                        )
                    }
                }
            }
        }
    }
}
