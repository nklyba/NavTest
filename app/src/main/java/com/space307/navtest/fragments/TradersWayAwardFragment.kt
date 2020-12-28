package com.space307.navtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.space307.navtest.R

class TradersWayAwardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_traders_way_award, container, false)

        view.findViewById<Button>(R.id.indicators_button).setOnClickListener {
            //TODO navigate to indicators
        }

        view.findViewById<Button>(R.id.deposit_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToDeposit)
        }

        view.findViewById<Button>(R.id.vip_materials_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToVipMaterials)
        }

        return view
    }
}