package com.example.proyectodashboard.pages

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.proyectodashboard.R
import com.example.proyectodashboard.R.string.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun Page_Flores(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier){
      items(favoriteCollectionsData.chunked(2)){row->
          Row{
             row.forEach{ card->
                 Card(
                     modifier = Modifier
                         .weight(1f)
                         .padding(8.dp)
                 ) {
                     val isFavorite = remember { mutableStateOf(false) }
                     Column(
                         horizontalAlignment =Alignment.CenterHorizontally
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
                             Text(stringResource(card.titulo))
                         }
                         Row(
                             modifier = Modifier,
                             horizontalArrangement = Arrangement.Start
                         ) {
                             Text(stringResource(card.descripcion))
                         }
                         Spacer(modifier = Modifier.height(16.dp))
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
                                 onClick = { /* Acción cuando se hace clic en el botón de moto */ },
                             ) {
                                 Icon(
                                     imageVector = Icons.Default.Person,
                                     contentDescription = "Moto"
                                 )
                             }
                         }
                         Spacer(modifier = Modifier.height(8.dp))
                         Row(modifier = Modifier) {
                             TextButton(
                                 onClick = { /* Acción cuando se hace clic en el botón de detalles */ },
                             ) {
                                 Text(text = "Detalles")
                             }
                             Spacer(modifier = Modifier.height(8.dp))
                             TextButton(
                                 onClick = { /* Acción cuando se hace clic en el botón de comprar */ },
                             ) {
                                 Text(text = "Comprar")
                             }
                         }

                     }

                 }
             }
          }
      }
    }

}

private val favoriteCollectionsData = listOf(
    Triple(R.drawable.bg_tienda_cba, canal_tienda, canal_tienda),
    Triple(R.drawable.bg_tienda_cba, canal_tienda, canal_tienda),
).map {DrawableStringPairFlores(it.first, it.second, it.third)}

private data class DrawableStringPairFlores(
    @DrawableRes val drawable: Int,
    @StringRes val titulo: Int,
    @StringRes val descripcion: Int
)



