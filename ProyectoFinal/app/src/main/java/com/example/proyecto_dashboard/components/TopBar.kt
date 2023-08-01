package com.example.proyecto_dashboard.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyecto_dashboard.pages.CarritoButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/*
 * Composable que representa la barra superior (TopAppBar) de la aplicación.
 */
@Composable
fun TopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    menuItem: List<MenuItem>,
) {
    // Estado para controlar la visibilidad del menú desplegable (DropdownMenu)
    var showMenu by remember {
        mutableStateOf(false)
    }

    // Obtiene la ruta actual de la pantalla
    var currentRoute = Current_Route(navController = navController)

    // Título inicial de la barra superior
    var myTytle = "Tienda Sena CBA"

    // Actualiza el título de la barra superior según la ruta actual
    menuItem.forEach { item ->
        if (currentRoute == item.ruta) myTytle = item.title
    }

    // Crea la barra superior (TopAppBar) con elementos interactivos
    TopAppBar(
        title = { Text(text = myTytle) }, // Título de la barra superior
        navigationIcon = {
            // Ícono para abrir el cajón (drawer)
            IconButton(
                onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open() // Abre el cajón (drawer)
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Icono de Menú"
                )
            }
        },
        actions = {
            // Botón del carrito de compras
            CarritoButton()

            // Ícono para mostrar el menú desplegable (DropdownMenu)
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Más opciones"
                )
            }

            // Menú desplegable (DropdownMenu) con opciones adicionales
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }, // Cierra el menú desplegable al hacer clic fuera de él
                modifier = Modifier.width(150.dp) // Ancho del menú desplegable

            ) {
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Idioma"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Idioma") // Opción para cambiar el idioma
                }
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = "Compartir"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Compartir") // Opción para compartir contenido
                }
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Configuración"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Configuración") // Opción para configurar ajustes
                }
            }
        }
    )
}
