package com.space307.navtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.space307.navtest.R

class MarketplaceScreenshotsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_marketplace_screenshots, container, false)

        val args: MarketplaceScreenshotsFragmentArgs by navArgs()
        val featureIdTextView = view.findViewById<TextView>(R.id.feature_id_text_view)
        featureIdTextView.text = "feature id: ${args.featureId}"

        val screenshotsTextView = view.findViewById<TextView>(R.id.feature_screenshots_text_view)
        screenshotsTextView.text = "screenshots: ${args.screenshotsUrlsList.joinToString()}"

        val positionTextView = view.findViewById<TextView>(R.id.feature_screenshots_position_text_view)
        positionTextView.text = "selected position: ${args.selectedScreenshotPosition}"

        return view
    }
}