package com.example.proyecto_dashboard.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/*
 * Composable que muestra un menú inferior (BottomAppBar) con elementos de navegación.
 *
 */
@Composable
fun BottomMenu(
    navController: NavHostController,
    menu_items_bar: List<Items_Bar>
) {
    BottomAppBar(
        cutoutShape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50))
    ) {
        BottomNavigation(
            modifier = Modifier.padding(
                0.dp,
                0.dp,
                60.dp,
                0.dp
            )
        ) {
            // Obtener la ruta actual para marcar el elemento de navegación seleccionado.
            val currentRouteBar = Current_Route(navController = navController)
            menu_items_bar.forEach { item ->
                BottomNavigationItem(
                    selected = currentRouteBar == item.ruta,
                    onClick = { navController.navigate(item.ruta) },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title, fontSize = 8.sp) }
                )
            }
        }
    }
}


/*
 * Composable que muestra un botón de acción flotante (FloatingActionButton) que muestra
 * una snackbar con un mensaje cuando se hace clic en él.
 */
@Composable
fun Fab(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    FloatingActionButton(
        onClick = {
            // Mostrar la snackbar cuando se hace clic en el botón.
            scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    "Próximamente Disponible!",
                    actionLabel = "Aceptar",
                    duration = SnackbarDuration.Indefinite
                )
            }
        },
    ) {
        Icon(
            imageVector = Icons.Filled.List,
            contentDescription = "Recompensas"
        )
    }
}
