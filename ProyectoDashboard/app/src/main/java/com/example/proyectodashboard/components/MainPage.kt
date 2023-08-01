package com.example.proyectodashboard.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController

@Composable
fun MainPage() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val navigationItems = listOf(
        MenuItem.Page01,
        MenuItem.Page02,
        MenuItem.Page03,
        MenuItem.Page04,
        MenuItem.Page05,
        MenuItem.Page06,
    )

    val  navigationItemsBottomBar = listOf(
        Items_Bar.Boton1,
        Items_Bar.Boton2,
        Items_Bar.Boton3,
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                scope,
                scaffoldState,
                navController,
                navigationItems
            )
        },
        drawerContent = {
            DrawerMenu(
                scope,
                scaffoldState,
                navController,
                menu_items = navigationItems
            )
        },
        bottomBar = {
            BottomMenu(
                navController,
                menu_items_bar = navigationItemsBottomBar
            )
        },
        floatingActionButton = {
            Fab(scope,
            scaffoldState)
        },
        isFloatingActionButtonDocked = true
    ) {padding ->
        ContentScaffold(
            padding = padding
        )
        Navigation_Host(navController)
    }
}

@Composable
fun ContentScaffold(padding: PaddingValues) {

}