package com.example.proyectodashboard.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.proyectodashboard.pages.ComprarButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

import com.example.proyectodashboard.pages.CarritoButton


@Composable
fun TopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    menuItem: List<MenuItem>,
) {
    var showMenu by remember{
        mutableStateOf(false)
    }
    var currentRoute = Current_Route(navController = navController)
    var myTytle = "Tienda Sena CBA"
    menuItem.forEach{item->
        if(currentRoute == item.ruta) myTytle = item.title
    }
    TopAppBar(
        title = { Text(text = myTytle)},
        navigationIcon = {
            IconButton(
                onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Icono de Menu"
                )
            }
        },
        actions = {
            CarritoButton()
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Mas opciones")
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false },
                modifier = Modifier.width(150.dp)

            ) {
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Idioma"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text( "idioma")
                }
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = "Compartir"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text( "Compartir")
                }
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Configuracion"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text( "Configuracion")
                }
            }
        }
    )
}



