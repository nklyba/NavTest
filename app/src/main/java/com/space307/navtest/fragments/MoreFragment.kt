package com.space307.navtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.space307.navtest.R

class MoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_more, container, false)

        view.findViewById<Button>(R.id.deposit_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToDeposit)
        }

        view.findViewById<Button>(R.id.withdraw_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToWithdraw)
        }

        view.findViewById<Button>(R.id.settings_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToSettings)
        }

        return view
    }
}