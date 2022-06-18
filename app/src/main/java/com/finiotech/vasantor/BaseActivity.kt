package com.finiotech.vasantor

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging
import com.finiotech.vasantorsdk.Vasantor
import com.finiotech.vasantorsdk.resources.VasantorContextWrapper

/**
 * Created by Sahidul on 2/17/2022.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LanguageHelper.setLanguage(this, LanguageHelper.getLanguage(this))

        /*Subscribe to Vasantor notification topic*/
        FirebaseMessaging.getInstance().subscribeToTopic(Vasantor.getNotificationTopic())
    }

    override fun attachBaseContext(newBase: Context) {
        /*Attach Context Wrapper with Vasantor*/
        super.attachBaseContext(VasantorContextWrapper(newBase).attach())
    }

}