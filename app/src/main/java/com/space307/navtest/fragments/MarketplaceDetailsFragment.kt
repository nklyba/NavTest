package com.space307.navtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.space307.navtest.R

class MarketplaceDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_marketplace_details, container, false)

        val args: MarketplaceDetailsFragmentArgs by navArgs()
        val featureIdTextView = view.findViewById<TextView>(R.id.feature_id_text_view)
        featureIdTextView.text = "feature id: ${args.featureId}"

        view.findViewById<Button>(R.id.feature_purchase_button).setOnClickListener {
            findNavController().navigate(
                MarketplaceDetailsFragmentDirections.navigateToFeaturePurchase(10)
            )
        }

        view.findViewById<Button>(R.id.feature_screenshots_button).setOnClickListener {
            findNavController().navigate(
                MarketplaceDetailsFragmentDirections.navigateToScreenshots(
                    featureId = 10,
                    screenshotsUrlsList = arrayOf("one","two","three"),
                    selectedScreenshotPosition = 1
                )
            )
        }

        return view
    }
}