package com.example.tdat1337.ovelse7

import android.os.Bundle
import android.preference.PreferenceFragment

class SettingsFragment: PreferenceFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)
    }
}