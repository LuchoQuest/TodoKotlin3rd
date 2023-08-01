package com.example.proyecto_dashboard.components

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.proyecto_dashboard.MainActivity
import com.example.proyecto_dashboard.R
import com.example.proyecto_dashboard.utils.Constants.channelId

/*
 * Case BroadcastReceiver para programar y mostrar notificaciones en un momento específico.
 */
class NotificacionProgramada : BroadcastReceiver() {

    /*
     * Objeto compañero (companion object) que contiene constantes utilizadas en la clase.
     */
    companion object {
        const val NOTIFICATION_ID = 5 // Identificador único de la notificación
    }

    /*
     * Método onReceive que se llama cuando se recibe una transmisión de broadcast.
     *
     */
    override fun onReceive(context: Context, intent: Intent?) {
        // Llama al método para crear y mostrar la notificación
        crearNotification(context)
        // TODO: Implementar acciones adicionales en respuesta a la recepción del broadcast.
    }

    /*
     * Método privado para crear y mostrar la notificación.
     *
     * @param context Contexto de la aplicación.
     */
    private fun crearNotification(context: Context) {
        // Crea un Intent para abrir la actividad principal (MainActivity)
        val intent = Intent(context, MainActivity::class.java).apply {
            // Define las banderas para el Intent
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        // Crea un PendingIntent con el Intent para abrir la actividad principal
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        // Crea la notificación utilizando el NotificationCompat.Builder
        val notification = NotificationCompat.Builder(
            context,
            channelId // Debes proporcionar el ID del canal de notificación
        )
            .setSmallIcon(R.drawable.ic_shopping_cart_24) // Icono pequeño de la notificación
            .setContentTitle("Notificación Programada") // Título de la notificación
            .setContentText("Saludos! Aprendiendo a programar notificaciones") // Texto del contenido de la notificación
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(
                        "Saludos! esta es una prueba de notificación " +
                                "programada; aparece después de un tiempo, incluso " +
                                "si la APP está cerrada. Podemos utilizar las otras " +
                                "características de una notificación que ya se han utilizado. " +
                                "Da clic para abrir la APP"
                    )
            ) // Estilo de texto de la notificación con texto largo
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) // Prioridad de la notificación
            .setContentIntent(pendingIntent) // Intent de la actividad a abrir al hacer clic en la notificación
            .setAutoCancel(true) // Hace que la notificación se cierre automáticamente al hacer clic en ella
            .build() // Crea la notificación

        // Obtiene el servicio de notificaciones del sistema
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // Muestra la notificación utilizando el identificador único
        manager.notify(
            NOTIFICATION_ID,
            notification
        )
    }
}
