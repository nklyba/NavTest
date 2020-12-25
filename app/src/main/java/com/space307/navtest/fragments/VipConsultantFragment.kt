package com.space307.navtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.space307.navtest.R

class VipConsultantFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_vip_consultant, container, false)

        view.findViewById<Button>(R.id.levels_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToLevels)
        }

        view.findViewById<Button>(R.id.phone_codes_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToPhoneCodes)
        }

        return view
    }
}