package com.example.proyecto_dashboard.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyecto_dashboard.R

/*
 * Composable que muestra la página de contenido del usuario con su información personal y una foto de perfil.
 */
@Composable
fun Page_Contenidos() {
    // Información del usuario
    val userData = UserData(
        name = "Rodolfa Perez",
        email = "Rodolfa@gmail.com",
        phoneNumber = "3124862888",
        city = "Bogota",
        country = "Colombia",
        bio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam rutrum condimentum est, eu efficitur tellus tempus sed. Duis feugiat viverra sem, a rhoncus justo fringilla at."
    )

    // Composable principal que muestra la información del usuario y su foto de perfil
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(Modifier.fillMaxSize()) {
            // Foto de perfil del usuario
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ub_sena),
                    contentDescription = "Foto de perfil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Columna con la información del usuario
            Column(Modifier.fillMaxWidth()) {
                // Nombre del usuario
                Text(
                    text = userData.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Email del usuario
                Text(
                    text = userData.email,
                    fontSize = 16.sp,
                    color = Color.Gray,
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Número de teléfono del usuario
                Text(
                    text = userData.phoneNumber,
                    fontSize = 16.sp,
                    color = Color.Gray,
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Ubicación del usuario (ciudad y país)
                Text(
                    text = "Location: ${userData.city}, ${userData.country}",
                    fontSize = 16.sp,
                    color = Color.Gray,
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Biografía del usuario
                Text(
                    text = userData.bio,
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botón para editar el perfil del usuario (acción del botón por definir)
                Button(
                    onClick = { /* Acción del botón */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Editar perfil")
                }
            }
        }
    }
}

/*
 * Clase de datos que representa la información del usuario.
 */
data class UserData(
    val name: String,
    val email: String,
    val phoneNumber: String,
    val city: String,
    val country: String,
    val bio: String
)
