package com.finiotech.vasantor

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.finiotech.vasantorsdk.Vasantor

/**
 * Created by Sahidul on 3/9/2022.
 */
class NotificationReceiver : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if (!Vasantor.isUpdateAvailable(remoteMessage.data)) {
            // Codes for App Notification
        }
    }
}