package com.finiotech.vasantor

import android.app.Application
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.finiotech.vasantorsdk.Vasantor

/**
 * Created by Sahidul Islam on 21-Dec-21.
 */
class VasantorApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        /*Initialize Vasantor SDK*/
        Vasantor.init(
            this,
            BuildConfig.VASANTOR_STORE_ID,
            BuildConfig.VASANTOR_STORE_PASSWORD,
            BuildConfig.VASANTOR_IS_PRODUCTION
        )

    }
}