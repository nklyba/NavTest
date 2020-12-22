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
import com.space307.navtest.utils.getNavigationBarTabFromGraphId
import com.space307.navtest.utils.setupWithNavController
import com.space307.navtest.views.NavigationBarView

class MarketplacePurchaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_marketplace_purchase, container, false)

        val args: MarketplaceDetailsFragmentArgs by navArgs()
        val featureIdTextView = view.findViewById<TextView>(R.id.feature_id_text_view)
        featureIdTextView.text = "feature id: ${args.featureId}"

        view.findViewById<Button>(R.id.deposit_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToDeposit)
        }

        return view
    }
}