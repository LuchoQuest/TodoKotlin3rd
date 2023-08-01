package com.example.proyecto_dashboard.pages

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class Question(val id: Int, val question: String, val answer: String)

@Composable
fun Page_Ver_Mas() {
    val faqList = remember {
        mutableStateListOf(
            Question(1, "¿Cuál es el horario de atención?", "Nuestro horario de atención es de lunes a viernes de 9am a 6pm."),
            Question(2, "¿Cuáles son los métodos de pago aceptados?", "Aceptamos tarjetas de crédito, transferencias bancarias y PayPal."),
            Question(3, "¿Cuál es el tiempo de envío?", "El tiempo de envío varía según la ubicación, generalmente oscila entre 3 y 7 días hábiles."),
            Question(4, "¿Ofrecen devoluciones o cambios?", "Sí, aceptamos devoluciones y cambios dentro de los 30 días posteriores a la compra.")
        )
    }
    val expandedQuestion = remember { mutableStateOf<Question?>(null) }

    Column {
        Text(
            text = "Preguntas frecuentes",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(16.dp)
        )
        Divider(color = Color.Gray, modifier = Modifier.padding(bottom = 8.dp))

        for (faq in faqList) {
            QuestionItem(faq, expandedQuestion.value == faq) {
                expandedQuestion.value = if (expandedQuestion.value == faq) null else faq
            }
        }
    }
}

@Composable
fun QuestionItem(question: Question, isExpanded: Boolean, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable(onClick = onItemClick)
            .animateContentSize(
                animationSpec = spring()
            ),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = question.question,
                style = MaterialTheme.typography.body1,
            )
            if (isExpanded) {
                Divider(color = Color.Gray, modifier = Modifier.padding(vertical = 8.dp))
                Text(
                    text = question.answer,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}


@Composable
fun AnswerItem(answer: String) {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = answer,
                style = MaterialTheme.typography.body2
            )
        }
    }
}
