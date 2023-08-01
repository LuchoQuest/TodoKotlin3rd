package com.example.proyecto_dashboard.pages

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.proyecto_dashboard.R

data class Product(val id: Int, val name: String, val imageResId: Int)

enum class ProductListType {
    NUEVO, POPULAR, TREND
}

@Composable
fun Page_Principal() {
    val popularProducts = listOf(
        Product(1, "Astromelia", R.drawable.flores1),
        Product(2, "Cubeta de huevos", R.drawable.huevos1),
        Product(3, "Leche", R.drawable.lacteos2),
        Product(4, "Leche de cabra", R.drawable.lacteos3),
    )
    val newProducts = listOf(
        Product(1, "Tomate", R.drawable.frutas6),
        Product(2, "Yogurt de cabra", R.drawable.lacteos1),
        Product(3, "Manzanas", R.drawable.frutas1),
        Product(4, "Peras", R.drawable.frutas2),
        Product(5, "Queso", R.drawable.lacteos4)
    )
    val trendingProducts = listOf(
        Product(1, "Kumis", R.drawable.lacteos6),
        Product(2, "Astromelia", R.drawable.flores5),
        Product(2, "Yogurt de cabra", R.drawable.lacteos1),
        Product(4, "Peras", R.drawable.frutas2),
        Product(5, "Queso", R.drawable.lacteos4)
    )

    val productListType = remember { mutableStateOf(ProductListType.POPULAR) }

    Column(modifier = Modifier.fillMaxSize()) {
        Row() {
            ButtonColumn(productListType.value) { type -> productListType.value = type }
            Carousel(
                productListType.value,
                popularProducts,
                newProducts,
                trendingProducts
            )
        }
        Row() {
            categorias()
        }
        Row {
            mostrarCategoria()
        }
    }

}

@Composable
fun ButtonColumn(
    selectedType: ProductListType,
    onButtonClick: (ProductListType) -> Unit
) {
    val buttonColors = ButtonDefaults.buttonColors(
        backgroundColor = Color.Transparent,
        contentColor = Color.White
    )

    Column(
        modifier = Modifier
            .width(90.dp)
            .padding(vertical = 30.dp)
            .height(200.dp),
        verticalArrangement = Arrangement.SpaceBetween, // Ajustar el espaciado vertical
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { onButtonClick(ProductListType.NUEVO) },
            colors = buttonColors,
            elevation = null,
            modifier = Modifier
                .rotate(-90f)
                .padding(bottom = 8.dp) // Espaciado inferior entre los botones
        ) {
            Text(
                text = "Nuevo",
                style = MaterialTheme.typography.button,
                color = if (selectedType == ProductListType.NUEVO) Color(0xFF00D604) else Color.LightGray
            )
        }
        Button(
            onClick = { onButtonClick(ProductListType.POPULAR) },
            colors = buttonColors,
            elevation = null,
            modifier = Modifier
                .rotate(-90f)
                .padding(bottom = 8.dp) // Espaciado inferior entre los botones
        ) {
            Text(
                text = "Popular",
                style = MaterialTheme.typography.button,
                color = if (selectedType == ProductListType.POPULAR) Color(0xFF00D604) else Color.LightGray
            )
        }
        Button(
            onClick = { onButtonClick(ProductListType.TREND) },
            colors = buttonColors,
            elevation = null,
            modifier = Modifier
                .rotate(-90f)
                .padding(bottom = 8.dp) // Espaciado inferior entre los botones
        ) {
            Text(
                text = "Trend",
                style = MaterialTheme.typography.button,
                color = if (selectedType == ProductListType.TREND) Color(0xFF00D604) else Color.LightGray
            )
        }
    }
}


@Composable
fun Carousel(
    productListType: ProductListType,
    popularProducts: List<Product>,
    newProducts: List<Product>,
    trendingProducts: List<Product>
) {
    val context = LocalContext.current
    val products = when (productListType) {
        ProductListType.NUEVO -> newProducts
        ProductListType.POPULAR -> popularProducts
        ProductListType.TREND -> trendingProducts
    }

    Column(modifier = Modifier
        .padding(horizontal = 1.dp)
        .padding(vertical = 30.dp)) {
        Text(
            text = productListType.name,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(products) { product ->
                Card(
                    modifier = Modifier
                        .width(200.dp)
                        .padding(8.dp),
                    elevation = 4.dp
                ) {
                    Box(modifier = Modifier.height(200.dp)) {
                        val imageBitmap =
                            ImageBitmap.imageResource(context.resources, product.imageResId)
                        Image(
                            bitmap = imageBitmap,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Surface(
                            color = Color.Black.copy(alpha = 0.2f),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.Bottom
                            ) {
                                Text(
                                    text = product.name,
                                    style = MaterialTheme.typography.body1,
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun categorias() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Categorías",
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CategorizedButton("Flores", painterResource(R.drawable.ic_flower))
            CategorizedButton("Huevos", painterResource(R.drawable.ic_egg))
            CategorizedButton("Frutas", painterResource(R.drawable.ic_fruit))
            CategorizedButton("Lacteos", painterResource(R.drawable.ic_leche))
        }
    }
}

@Composable
fun CategorizedButton(text: String, icon: Painter) {
    OutlinedButton(
        onClick = { /* Acción de categoría */ },
        modifier = Modifier.padding(8.dp),
        elevation = ButtonDefaults.elevation(2.dp)
    ) {
        Column {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
                    .align(Alignment.CenterHorizontally),
                tint = Color(0xFF00D604)
            )
            Text(text = text)
        }
    }
}

@Composable
fun mostrarCategoria() {
    var selectedProduct by remember { mutableStateOf<DrawableStringPairFlores1?>(null) }

    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(favoriteCollectionsData1.chunked(2)) { row ->
            Row {
                row.forEach { card ->
                    Card(
                        modifier = Modifier
                            .width(200.dp)
                            .padding(8.dp)
                    ) {
                        val isFavorite = remember { mutableStateOf(false) }

                        Box(modifier = Modifier.aspectRatio(1f)) {
                            Image(
                                painter = painterResource(card.drawable),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                            Surface(
                                color = Color.Black.copy(alpha = 0.2f),
                                modifier = Modifier.fillMaxWidth()
                            ) {

                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(8.dp)
                                ) {

                                    Text(
                                        stringResource(card.titulo),
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Row(
                                        verticalAlignment = Alignment.Bottom,
                                    ) {
                                        ComprarButton()
                                        IconButton(
                                            onClick = { isFavorite.value = !isFavorite.value },
                                            modifier = Modifier
                                                .clip(shape = CircleShape)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Favorite,
                                                contentDescription = "Corazón",
                                                tint = if (isFavorite.value) Color.Red else Color.White
                                            )
                                        }
                                        IconButton(
                                            onClick = { selectedProduct = card },
                                            modifier = Modifier.weight(1f),
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Info,
                                                contentDescription = "Info",
                                                tint = Color.White
                                            )
                                        }


                                    }
                                }
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
                        Row {
                            TextButton(
                                onClick = { selectedProduct = null },
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(text = "Cerrar")
                            }
                            TextButton(
                                onClick = {
                                    contadorState++
                                    selectedProduct = null
                                },
                                modifier = Modifier.weight(1f)
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

private val favoriteCollectionsData1 = listOf(
    Triple(R.drawable.flores1, R.string.flores1, R.string.descflores1),
    Triple(R.drawable.flores2, R.string.flores2, R.string.descflores2),
    Triple(R.drawable.flores3, R.string.flores3, R.string.descflores3),
    Triple(R.drawable.flores4, R.string.flores4, R.string.descflores4),
    Triple(R.drawable.flores5, R.string.flores5, R.string.descflores5),
    Triple(R.drawable.flores6, R.string.flores6, R.string.descflores6),
).map {DrawableStringPairFlores1(it.first, it.second, it.third)}

private data class DrawableStringPairFlores1(
    @DrawableRes val drawable: Int,
    @StringRes val titulo: Int,
    @StringRes val descripcion: Int
)