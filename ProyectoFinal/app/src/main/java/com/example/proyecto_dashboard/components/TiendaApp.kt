package com.example.proyecto_dashboard.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.proyecto_dashboard.pages.*
import com.example.proyecto_dashboard.pages.login.LoginScreen
import com.example.proyecto_dashboard.pages.login.Pantallas
import com.example.proyecto_dashboard.pages.login.SplashScreen

enum class PageScreen() {
    Login,
    DashBoard
}
/*
 * Composable principal de la aplicación "TiendaApp" que contiene el Scaffold y el NavHost
 * para manejar la navegación entre pantallas.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TiendaApp(
    navController: NavHostController = rememberNavController()
) {
    Scaffold() { padding ->
        ScaffoldContent(padding = padding)
        NavHost(
            navController = navController,
            startDestination = Pantallas.SplashScreen.ruta
        ) {
            composable(route = PageScreen.Login.name) {
                LoginScreen(navController = navController)
            }
            composable(route = MenuItem.Page01.ruta) {
                MainPage()
            }
            composable(Pantallas.SplashScreen.ruta) {
                SplashScreen(navController)
            }
        }
    }
}


@Composable
fun ScaffoldContent(//1
    padding: PaddingValues,
) {
    Column(//(2)
        modifier = Modifier
            .padding(
                top = 16.dp,
                bottom = padding.calculateBottomPadding()
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row { // (3)
        }
    }
}

@Composable
fun Navigation_Host(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = MenuItem.Page01.ruta
    ) {
        composable(Pantallas.TiendaApp.ruta) {
            TiendaApp()
        }
        composable(MenuItem.Page01.ruta){
            Page_Principal()
        }
        composable(MenuItem.Page02.ruta){
            Page_Flores()
        }
        composable(MenuItem.Page03.ruta){
            Page_Frutas_Verduras()
        }
        composable(MenuItem.Page04.ruta){
            Page_Huevos()
        }
        composable(MenuItem.Page05.ruta){
            Page_Lacteos()
        }
        composable(MenuItem.Page06.ruta){
            Page_Ver_Mas()
        }
        composable(Items_Bar.Boton2.ruta){
            Page_Contenidos()
        }
        composable(Items_Bar.Boton4.ruta){
            Page_Ubicacion()
        }
    }
}

@Composable
fun Current_Route(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}