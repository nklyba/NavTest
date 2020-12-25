package com.space307.navtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.space307.navtest.R

class MarketplaceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_marketplace, container, false)

        view.findViewById<Button>(R.id.feature_purchase_button).setOnClickListener {
            findNavController().navigate(
                MarketplaceFragmentDirections.navigateToFeaturePurchase(10)
            )
        }

        view.findViewById<Button>(R.id.feature_details_button).setOnClickListener {
            findNavController().navigate(
                MarketplaceFragmentDirections.navigateToFeatureDetails(10)
            )
        }

        return view
    }
}