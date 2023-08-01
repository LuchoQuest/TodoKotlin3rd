package com.example.proyecto_dashboard.pages.login

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.proyecto_dashboard.R
import com.example.proyecto_dashboard.components.CreateChannelNotification
import com.example.proyecto_dashboard.components.MenuItem
import com.example.proyecto_dashboard.components.notificacionImagen
import com.example.proyecto_dashboard.components.notificacionSencilla

@Composable
fun LoginScreen(navController: NavHostController) {
    //Variable para controlar la accion: True::Login - false::Create
    val showLoginForm = rememberSaveable {
        mutableStateOf(true)
    }

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = R.drawable.splash_bienvenida)
                    .apply(block = fun ImageRequest.Builder.() {
                        size(Size.ORIGINAL)
                    }).build(),
                imageLoader = imageLoader
            ),
            contentDescription = null,
            modifier  = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        BoxWithConstraints (
            modifier = Modifier
                ) {
            //Valores para la posicion de la Box
            val boxWidth = maxWidth
            val boxHeight = maxHeight


            Box(
                modifier = Modifier
                    .offset(
                        //Horizontal
                        x = (boxWidth - 400.dp) / 2,
                        //Vertical
                        y = (boxHeight - 380.dp) / 2
                    )
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Surface(
                    color = Color.White.copy(alpha = 0.2f),
                    modifier = Modifier.fillMaxWidth()
                ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    if (showLoginForm.value) {
                        Text(
                            text = "Inicia Sesión",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp
                        )
                        UserForm(
                            isCreateAccount = false,
                            { email, password ->
                                Log.d("TiendaApp", "Inicio sesion con $email y $password")
                            },
                            navController = navController
                        )
                    } else {
                        Text(
                            text = "Crear Cuenta Nueva"
                        )
                        UserForm(
                            isCreateAccount = true,
                            { email, password ->
                                Log.d("TiendaApp", "Creando Cuenta con $email y $password")
                            },
                            navController = navController
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .height(15.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val text1 =
                            if (showLoginForm.value)
                                "¿No Tienes Cuenta?"
                            else
                                "¿Ya Tienes Cuenta?"
                        val text2 =
                            if (showLoginForm.value)
                                "Registrate"
                            else
                                "Inicia Sesión"
                        Text(
                            text = text1,
                            color = Color.White
                        )
                        Text(
                            text = text2,
                            modifier = Modifier
                                .clickable { showLoginForm.value = !showLoginForm.value }
                                .padding(start = 5.dp),
                            color = Color.Green
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(
    isCreateAccount: Boolean,
    onDone: (String, String) -> Unit = { email, pwd -> },
    navController: NavHostController
) {
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }
    val passwordVisible = rememberSaveable {
        mutableStateOf(false)
    }
    val validState = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailInput(
            emailState = email
        )
        PasswordInput(
            passwordState = password,
            labelId = "Password",
            passwordVisible = passwordVisible
        )
        Spacer(
            modifier = Modifier
                .height(15.dp)
        )
        SubmitButton(
            textId =
            if (isCreateAccount)
                "Crear Cuenta"
            else
                "Iniciar Sesión",
            inputValid = validState,
            isCreateAccount = isCreateAccount,
            onClic = {
                onDone(email.value.trim(), password.value.trim())
                keyboardController?.hide()
             },
            navController = navController
        )
    }
}

@Composable
fun SubmitButton(
    textId: String,
    inputValid: Boolean,
    isCreateAccount: Boolean,
    onClic: () -> Unit,
    navController: NavHostController
) {
    //Valores de notificaciones
    val idNotification = 0
    val context: Context = LocalContext.current
    val idChannel: String = stringResource(R.string.canal_tienda)
    val name = stringResource(R.string.canal_tienda)
    val descriptionText = stringResource(R.string.canal_notificaciones)

    val textShort = "Su Inicio de Sesion se a realizado con exito."
    val textLong: String = "Saludos! Usted se a Registrado Exitosamente " +
            "El CBA le agradece que nos acompañe en cada momento "

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

    Button(
        onClick = {
            onClic()
            if (isCreateAccount) {
                // Enviar notificación de bienvenida al registrarse
                notificacionImagen(
                    context,
                    idChannel,
                    idNotification + 2,
                    "Notificacion De Registro",
                    textLong,
                    imagenBig
                )
            } else {
                // Enviar notificación de inicio de sesión exitoso
                notificacionSencilla(
                    context,
                    idChannel,
                    idNotification,
                    "Notificacion Login",
                    textShort
                )
            }
            navController.navigate(MenuItem.Page01.ruta)
        },
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape,
        enabled = inputValid,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Green,
            contentColor = Color.Black
        )
    ) {
        Text(
            text = textId,
            modifier = Modifier
                .padding(5.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    passwordState: MutableState<String>,
    labelId: String,
    passwordVisible: MutableState<Boolean>
) {
    val visualTransformation =
        if (passwordVisible.value)
            VisualTransformation.None
        else
            PasswordVisualTransformation()

    OutlinedTextField(
        value = passwordState.value,
        onValueChange = { passwordState.value = it },
        label = { Text(text = labelId,color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        modifier = Modifier
            .padding(
                bottom = 10.dp,
                start = 10.dp,
                end = 10.dp
            )
            .fillMaxWidth(),
        visualTransformation = visualTransformation,
        trailingIcon = {
            if (passwordState.value.isNotBlank())
                PasswordVisibleIcon(passwordVisible)
            else null
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Green,
            unfocusedBorderColor = Color.Green.copy(alpha = 0.5f),
            textColor = Color.White
        )
    )
}

@Composable
fun PasswordVisibleIcon(
    passwordVisible: MutableState<Boolean>
) {
    val image =
        if (passwordVisible.value)
            Icons.Default.Visibility
        else
            Icons.Default.VisibilityOff
    IconButton(
        onClick = {
            passwordVisible.value = !passwordVisible.value
        }) {
        Icon(
            imageVector = image,
            contentDescription = "",
            tint = Color.Green
        )
    }
}

@Composable
fun EmailInput(
    emailState: MutableState<String>,
    labelId: String = "Email"
) {
    InputField(
        valueState = emailState,
        labelId = labelId,
        keyboardType = KeyboardType.Email
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    valueState: MutableState<String>,
    labelId: String,
    keyboardType: KeyboardType,
    isSingleLine: Boolean = true
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId,color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp) },
        singleLine = isSingleLine,
        modifier = Modifier
            .padding(
                bottom = 10.dp,
                start = 10.dp,
                end = 10.dp
            )
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Green,
            unfocusedBorderColor = Color.Green.copy(alpha = 0.5f),
            textColor = Color.White
        )
    )
}
