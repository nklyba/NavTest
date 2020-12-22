package com.space307.navtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.space307.navtest.R

class HelpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_help, container, false)

        view.findViewById<Button>(R.id.support_contacts_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToSupportContacts)
        }

        view.findViewById<Button>(R.id.vip_materials_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToVipMaterials)
        }

        view.findViewById<Button>(R.id.vip_consultant_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToVipConsultant)
        }

        view.findViewById<Button>(R.id.faq_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToFaq)
        }

        view.findViewById<Button>(R.id.education_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToEducation)
        }

        view.findViewById<Button>(R.id.insights_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToInsights)
        }

        view.findViewById<Button>(R.id.trading_signals_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToTradingSignals)
        }

        return view
    }
}