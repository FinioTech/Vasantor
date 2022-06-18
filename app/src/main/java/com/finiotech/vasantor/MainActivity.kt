package com.finiotech.vasantor

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.finiotech.vasantor.databinding.ActivityMainBinding
import com.finiotech.vasantorsdk.Vasantor
import com.finiotech.vasantorsdk.repository.listener.VasantorCallback

class MainActivity : BaseActivity() {

    private lateinit var layoutBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutBinding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)

        /*Implement Vasantor callback*/
        Vasantor.updateCallback(object : VasantorCallback {
            override fun onUpdated() {
                buildUI()
            }

            override fun onError(error: String?) {
                Log.e( "onError: ", error.toString())
            }
        })

        /*Update Vasantor Translations*/
        Vasantor.updateTranslations()

        initListener()
    }

    private fun buildUI() {
        layoutBinding.wellComeBackTextView.text = getString(R.string.welcome_back)
        layoutBinding.wellComeBackAgainTextView.text = getString(R.string.welcome_back_again)
        layoutBinding.keyOneTextView.text = getString(R.string.key_1)
    }

    private fun initListener() {
        layoutBinding.englishBtn.setOnClickListener {
            LanguageHelper.setLanguage(this, "en")
            recreate()
        }

        layoutBinding.arabicBtn.setOnClickListener {
            LanguageHelper.setLanguage(this, "ar")
            recreate()
        }

        layoutBinding.kurdishBtn.setOnClickListener {
            LanguageHelper.setLanguage(this, "ku")
            recreate()
        }

        layoutBinding.italicBtn.setOnClickListener {
            LanguageHelper.setLanguage(this, "it")
            recreate()
        }

        layoutBinding.banglaBtn.setOnClickListener {
            LanguageHelper.setLanguage(this, "bn")
            recreate()
        }
    }
}