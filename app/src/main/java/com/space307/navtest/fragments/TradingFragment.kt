package com.space307.navtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.space307.navtest.R
import com.space307.navtest.utils.getNavigationBarTabFromGraphId
import com.space307.navtest.utils.setupWithNavController
import com.space307.navtest.views.NavigationBarView

class TradingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trading, container, false)
    }
}