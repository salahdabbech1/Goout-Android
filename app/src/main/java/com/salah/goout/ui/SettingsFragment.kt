package com.salah.goout.ui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.salah.goout.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}