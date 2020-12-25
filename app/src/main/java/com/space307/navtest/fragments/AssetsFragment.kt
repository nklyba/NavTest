package com.space307.navtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.space307.navtest.R
import com.space307.navtest.data.PlatformType
import com.space307.navtest.utils.PreferenceProvider
import com.space307.navtest.utils.PreferenceProvider.PLATFORM_KEY
import com.space307.navtest.utils.setTradingPlatform

class AssetsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_assets, container, false)

        view.findViewById<Button>(R.id.select_op_asset_button).setOnClickListener {
            PreferenceProvider.put(PLATFORM_KEY, PlatformType.OPTIONS_MODE.key)

            setTradingPlatform(findNavController())
        }

        view.findViewById<Button>(R.id.select_fx_asset_button).setOnClickListener {
            PreferenceProvider.put(PLATFORM_KEY, PlatformType.FOREX_MODE.key)

            setTradingPlatform(findNavController())
        }

        return view
    }
}