package com.finiotech.vasantor

import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.LocaleList
import java.util.*

/**
 * Created by Sahidul on 2/28/2022.
 */
internal object LanguageHelper {
    private const val SELECTED_LANGUAGE = "Language"
    const val PREF_NAME = "languagePref"

    // returns Context having application default locale for all activities
    fun onAttach(context: Context): Context? {
        val lang = getPersistedData(context, Locale.getDefault().language)
        return setLanguage(context, lang)
    }

    // sets application locale with default locale persisted in preference manager on each new launch of application and
    // returns Context having application default locale
    fun onAttach(context: Context, defaultLanguage: String?): Context? {
        val lang = getPersistedData(context, defaultLanguage)
        return setLanguage(context, lang)
    }

    // returns language code persisted in preference manager
    fun getLanguage(context: Context): String? {
        return getPersistedData(context, Locale.getDefault().language)
    }

    private fun getPref(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    // persists new language code change in preference manager and updates application default locale
    // returns Context having application default locale
    fun setLanguage(context: Context, language: String?): Context? {
        persist(context, language)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, language)
        } else updateResourcesLegacy(context, language)
    }

    // returns language code persisted in preference manager
    fun getPersistedData(context: Context, defaultLanguage: String?): String? {
        val preferences = getPref(context)
        return preferences.getString(SELECTED_LANGUAGE, defaultLanguage)
    }

    // persists new language code in preference manager
    private fun persist(context: Context, language: String?) {
        val preferences = getPref(context)
        val editor = preferences.edit()
        editor.putString(SELECTED_LANGUAGE, language)
        editor.apply()
    }

    // For android device versions above Nougat (7.0)
    // updates application default locale configurations and
    // returns new Context object for the current Context but whose resources are adjusted to match the given Configuration
    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, language: String?): Context? {
        val locale = Locale(language!!)
        val configuration = context.resources.configuration
        val localeList = LocaleList(locale)
        LocaleList.setDefault(localeList)
        configuration.setLocales(localeList)
        return context.createConfigurationContext(configuration)
    }

    // For android device versions below Nougat (7.0)
    // updates application default locale configurations and
    // returns new Context object for the current Context but whose resources are adjusted to match the given Configuration
    private fun updateResourcesLegacy(context: Context, language: String?): Context? {
        val locale = Locale(language!!)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }
}