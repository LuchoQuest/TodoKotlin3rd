package com.example.proyecto_dashboard.pages

import android.content.Context
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.proyecto_dashboard.R
import com.example.proyecto_dashboard.R.string.*
import com.example.proyecto_dashboard.components.CreateChannelNotification



var contadorState by mutableStateOf(0)



/*
 * Composable que representa una página de la aplicación TiendaApp con una lista de elementos de flores.
 * Permite ver los detalles de un producto seleccionado y mostrar una notificación al hacer clic en un botón.
 */
@Composable
fun Page_Flores(modifier: Modifier = Modifier) {
    // Estado para mantener el producto seleccionado y mostrar su detalle en un diálogo.
    var selectedProduct by remember { mutableStateOf<DrawableStringPairFlores?>(null) }

    // Código para la creación del canal de notificación al inicio del composable.
    // Este canal se crea una sola vez al inicio del composable.
    val idNotification: Int = 0
    val context: Context = LocalContext.current
    val idChannel: String = stringResource(R.string.canal_tienda)
    val name = stringResource(R.string.canal_tienda)
    val descriptionText = stringResource(R.string.canal_notificaciones)
    val textLong: String = "Bienvenido a la tienda Sena. Este es el auditorio."
    val imagenBig = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.bg_tienda_cba
    )

    //Función de creación propia como corrutina para crear el canal de notificación.
    LaunchedEffect(Unit) {
        CreateChannelNotification(
            idChannel,
            context,
            name,
            descriptionText
        )
    }

    // Composable LazyColumn que muestra una lista de elementos de flores en filas de a dos.
    LazyColumn(modifier = modifier) {
        items(favoriteCollectionsData.chunked(2)) { row ->
            Row {
                row.forEach { card ->
                    // Composable Card para mostrar la información de cada producto.
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    ) {
                        // Estado para mantener el estado de favorito del producto.
                        val isFavorite = remember { mutableStateOf(false) }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(card.drawable),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(200.dp)
                            )
                            // Otros elementos de UI para mostrar información sobre el producto.
                            // ...

                            // Botón para mostrar los detalles del producto en un diálogo.
                            Row(modifier = Modifier) {
                                TextButton(
                                    onClick = { selectedProduct = card },
                                ) {
                                    Text(text = "Detalles")
                                }
                                ComprarButton()
                            }
                        }
                    }
                }
            }
        }
    }

    // Diálogo para mostrar los detalles del producto seleccionado.
    selectedProduct?.let { product ->
        Dialog(
            onDismissRequest = { selectedProduct = null },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true),
            content = {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(Color.White)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    // Contenido del diálogo que muestra la información del producto seleccionado.
                    // ...
                }
            }
        )
    }
}

/*
 * Clase de datos que representa una lista de productos de flores.
 */
private data class DrawableStringPairFlores(
    @DrawableRes val drawable: Int,
    @StringRes val titulo: Int,
    @StringRes val descripcion: Int
)

/*
 * Listado de productos de flores junto con sus detalles (título y descripción).
 * Los productos se muestran en la lista de elementos de la página Page_Flores.
 */
private val favoriteCollectionsData = listOf(
    Triple(R.drawable.flores1, flores1, descflores1),
    Triple(R.drawable.flores2, flores2, descflores2),
    Triple(R.drawable.flores3, flores3, descflores3),
    Triple(R.drawable.flores4, flores4, descflores4),
    Triple(R.drawable.flores5, flores5, descflores5),
    Triple(R.drawable.flores6, flores6, descflores6),
).map { DrawableStringPairFlores(it.first, it.second, it.third) }

/*
 * Composable que muestra un botón para comprar productos. Incrementa el contador de compra al hacer clic.
 */
@Composable
fun ComprarButton() {
    OutlinedButton(
        onClick = {
            contadorState++
        }
    ) {
        Text(text = "Comprar")
    }
}

/*
 * Composable que muestra un ícono de carrito de compras y un contador de la cantidad de productos comprados.
 */
@Composable
fun CarritoButton() {
    Box(modifier = Modifier.padding(top = 8.dp)) {
        IconButton(onClick = { /* TODO: Acción al hacer clic */ }) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "Refrescar"
            )
        }

        if (contadorState > 0) {
            // Mostrar el contador solo si el valor de contadorState es mayor que 0.
            Text(
                text = contadorState.toString(),
                modifier = Modifier
                    .size(18.dp)
                    .clip(CircleShape)
                    .background(Color.Red)
                    .padding(2.dp)
                    .align(Alignment.TopEnd),
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
}
