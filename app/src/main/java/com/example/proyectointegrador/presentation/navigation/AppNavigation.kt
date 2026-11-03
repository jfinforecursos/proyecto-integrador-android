package com.example.proyectointegrador.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.proyectointegrador.presentation.screens.ClienteScreen
import com.example.proyectointegrador.presentation.screens.DetalleVehiculoScreen
import com.example.proyectointegrador.presentation.screens.HomeScreen
import com.example.proyectointegrador.presentation.screens.VehiculoScreen
import com.example.proyectointegrador.presentation.viewmodel.ClienteViewModel
import com.example.proyectointegrador.presentation.viewmodel.VehiculoViewModel

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    vehiculoViewModel: VehiculoViewModel = viewModel(),
    clienteViewModel: ClienteViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Home
    ) {
        composable<Destination.Home> {
            HomeScreen(
                vehiculoViewModel = vehiculoViewModel,
                clienteViewModel = clienteViewModel,
                onNavigateToVehiculos = { navController.navigate(Destination.Vehiculos) },
                onNavigateToClientes = { navController.navigate(Destination.Clientes) },
                onNavigateToDetalle = { vehiculo ->
                    navController.navigate(
                        Destination.DetalleVehiculo(
                            id = vehiculo.id,
                            marca = vehiculo.marca,
                            modelo = vehiculo.modelo,
                            precioPorDia = vehiculo.precioPorDia
                        )
                    )
                }
            )
        }

        composable<Destination.Vehiculos> {
            VehiculoScreen(
                viewModel = vehiculoViewModel,
                onNavigateBack = { navController.popBackStack() },
                onVerDetalle = { vehiculo ->
                    navController.navigate(
                        Destination.DetalleVehiculo(
                            id = vehiculo.id,
                            marca = vehiculo.marca,
                            modelo = vehiculo.modelo,
                            precioPorDia = vehiculo.precioPorDia
                        )
                    )
                }
            )
        }

        composable<Destination.Clientes> {
            ClienteScreen(
                viewModel = clienteViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable<Destination.DetalleVehiculo> { backStackEntry ->
            val destination: Destination.DetalleVehiculo = backStackEntry.toRoute()
            DetalleVehiculoScreen(
                id = destination.id,
                marca = destination.marca,
                modelo = destination.modelo,
                precioPorDia = destination.precioPorDia,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
