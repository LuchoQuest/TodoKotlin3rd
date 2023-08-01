package com.example.proyectodashboard.components

import android.icu.text.CaseMap.Title
import com.example.proyectodashboard.R


sealed class Items_Bar (
    val icon: Int,
    val title: String,
    val ruta: String
    ){
    object Boton1: Items_Bar(R.drawable.ic_location, "Ubicacion", "boton1")
    object Boton2: Items_Bar(R.drawable.ic_person, "Perfil", "boton2")
    object Boton3: Items_Bar(R.drawable.ic_recycling_24, "Informacion", "boton3")



}