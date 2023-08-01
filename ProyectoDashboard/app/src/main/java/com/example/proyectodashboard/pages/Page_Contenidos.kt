package com.example.proyectodashboard.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
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
import com.example.proyectodashboard.R

@Composable
fun Page_Contenidos() {
    val userData = UserData(
        name = "Rodolfa Perez",
        email = "Rodolfa@gmail.com",
        phoneNumber = "3124862888",
        city = "Bogota",
        country = "Colombia",
        bio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam rutrum condimentum est, eu efficitur tellus tempus sed. Duis feugiat viverra sem, a rhoncus justo fringilla at."
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(Modifier.fillMaxSize()) {
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

            Column(Modifier.fillMaxWidth()) {
                Text(
                    text = userData.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = userData.email,
                    fontSize = 16.sp,
                    color = Color.Gray,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = userData.phoneNumber,
                    fontSize = 16.sp,
                    color = Color.Gray,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Location: ${userData.city}, ${userData.country}",
                    fontSize = 16.sp,
                    color = Color.Gray,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = userData.bio,
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

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

data class UserData(
    val name: String,
    val email: String,
    val phoneNumber: String,
    val city: String,
    val country: String,
    val bio: String
)
