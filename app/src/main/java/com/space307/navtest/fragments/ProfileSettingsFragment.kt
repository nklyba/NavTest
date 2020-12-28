package com.space307.navtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.space307.navtest.R

class ProfileSettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile_settings, container, false)

        view.findViewById<Button>(R.id.email_confirmation_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToEmailConfirmation)
        }

        view.findViewById<Button>(R.id.account_verification_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToAccountVerification)
        }

        return view
    }
}