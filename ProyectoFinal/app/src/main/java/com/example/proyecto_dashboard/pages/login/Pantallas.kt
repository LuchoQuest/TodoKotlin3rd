package com.example.proyecto_dashboard.pages.login


// se inicializan las rutas
sealed class Pantallas (val ruta: String) {
    object SplashScreen: Pantallas("splash_screen")
    object TiendaApp: Pantallas("tienda_app")
}