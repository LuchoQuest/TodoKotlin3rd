package com.example.proyectodashboard.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun Page_Ver_Mas() {
    Column {
        Text(text = "Ver Mas",
            style = MaterialTheme.typography.h2
        )
    }
}