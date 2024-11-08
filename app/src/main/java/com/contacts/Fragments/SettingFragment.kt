package com.contacts.Fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.contacts.R
import androidx.appcompat.app.AppCompatDelegate
import java.util.*

class SettingFragment : Fragment() {

    private lateinit var switchDarkMode: Switch
    private lateinit var spinnerLanguage: Spinner
    private lateinit var versionTextView: TextView

    private var isDarkMode: Boolean = false
    private var selectedLanguage: String = "en"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        switchDarkMode = view.findViewById(R.id.switch_dark_mode)
        spinnerLanguage = view.findViewById(R.id.spinner_language)
        versionTextView = view.findViewById(R.id.settings_title)

        loadSettings()

        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            isDarkMode = isChecked
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            )
        }

        spinnerLanguage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedLanguage = when (position) {
                    1 -> "fr"  // French
                    2 -> "ar"  // Arabic
                    else -> "en"  // English
                }
                setLocale(selectedLanguage)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        return view
    }

    private fun loadSettings() {
        val languagePosition = resources.getStringArray(R.array.language_options).indexOf(selectedLanguage)
        spinnerLanguage.setSelection(languagePosition)
    }

    private fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)

        val context = requireActivity().createConfigurationContext(config)


        requireActivity().resources.updateConfiguration(config, requireActivity().resources.displayMetrics)

        loadSettings()
    }
}
