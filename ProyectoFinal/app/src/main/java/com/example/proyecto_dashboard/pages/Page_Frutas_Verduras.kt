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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.proyecto_dashboard.R
import com.example.proyecto_dashboard.components.CreateChannelNotification
import com.example.proyecto_dashboard.components.notificacionImagen

@Composable
fun Page_Frutas_Verduras(modifier: Modifier = Modifier) {
    var selectedProduct by remember { mutableStateOf<DrawableStringPairFlores2?>(null) }

    val idNotification: Int = 0
    val context: Context = LocalContext.current
    val idChannel: String = stringResource(R.string.canal_tienda)
    val name = stringResource(R.string.canal_tienda)
    val descriptionText = stringResource(R.string.canal_notificaciones)

    val textLong: String = "Bienvenido a la tienda sena " +
            "Este es el auditorio"

    val imagenBig = BitmapFactory.decodeResource(
        context.resources,
        R.drawable.bg_tienda_cba
    )

    //Funcion de creacion propia como corrutina
    LaunchedEffect(Unit) {
        CreateChannelNotification(
            idChannel,
            context,
            name,
            descriptionText
        )
    }


    LazyColumn(modifier = modifier){
        items(favoriteCollectionsData2.chunked(2)){
                row->
            Row{

                row.forEach{ card->
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    ) {
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
                            Row(
                                modifier = Modifier,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Text(
                                    stringResource(card.titulo),
                                    fontWeight = FontWeight.Bold)
                            }
                            Row(
                                modifier = Modifier,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Text(stringResource(card.descripcion))
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            Row(modifier = Modifier) {
                                IconButton(
                                    onClick = { isFavorite.value = !isFavorite.value },
                                    modifier = Modifier
                                        .clip(shape = CircleShape)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = "Corazón",
                                        tint = if (isFavorite.value) Color.Red else Color.Unspecified
                                    )
                                }
                                IconButton(
                                    onClick = {
                                        notificacionImagen(
                                            context,
                                            idChannel,
                                            idNotification + 2,
                                            "Bienvenido al sena",
                                            textLong,
                                            imagenBig
                                        )
                                    },
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = "Moto"
                                    )
                                }
                            }
                            Row(modifier = Modifier) {
                                TextButton(
                                    onClick = {
                                        selectedProduct = card
                                    },
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
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(product.drawable),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(200.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(product.titulo),
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = stringResource(product.descripcion),
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row() {
                            TextButton(
                                onClick = { selectedProduct = null },
                            ) {
                                Text(text = "Cerrar")
                            }
                            TextButton(
                                onClick = {
                                    contadorState++
                                    selectedProduct = null
                                }
                            ) {
                                Text(text = "Comprar")
                            }
                        }
                    }
                }
            }
        )
    }

}

private val favoriteCollectionsData2 = listOf(
    Triple(R.drawable.frutas1, R.string.frutas1, R.string.descfrutas1),
    Triple(R.drawable.frutas2, R.string.frutas2, R.string.descfrutas2),
    Triple(R.drawable.frutas3, R.string.frutas3, R.string.descfrutas3),
    Triple(R.drawable.frutas4, R.string.frutas4, R.string.descfrutas4),
    Triple(R.drawable.frutas5, R.string.frutas5, R.string.descfrutas5),
    Triple(R.drawable.frutas6, R.string.frutas6, R.string.descfrutas6),
).map {DrawableStringPairFlores2(it.first, it.second, it.third)}

private data class DrawableStringPairFlores2(
    @DrawableRes val drawable: Int,
    @StringRes val titulo: Int,
    @StringRes val descripcion: Int
)


