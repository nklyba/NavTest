package com.space307.navtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.space307.navtest.R
import com.space307.navtest.utils.getNavigationBarTabFromGraphId
import com.space307.navtest.utils.setupWithNavController
import com.space307.navtest.views.NavigationBarView

class HelpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_help, container, false)

        view.findViewById<Button>(R.id.trading_signals_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToTradingSignals)
        }

        return view
    }
}