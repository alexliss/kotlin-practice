package com.redspot.kotlinpractice.framework.ui.settings

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceFragmentCompat
import com.redspot.kotlinpractice.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.design_default_color_background));
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}