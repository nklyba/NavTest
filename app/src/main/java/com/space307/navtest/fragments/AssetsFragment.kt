package com.space307.navtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.space307.navtest.R
import com.space307.navtest.data.NavigationBarTabType
import com.space307.navtest.data.PlatformType
import com.space307.navtest.utils.PreferenceProvider
import com.space307.navtest.utils.PreferenceProvider.PLATFORM_KEY
import com.space307.navtest.utils.changeTabNavigationGraph

class AssetsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_assets, container, false)

        view.findViewById<Button>(R.id.select_op_asset_button).setOnClickListener {
            val currentPlatform = PlatformType.forType(
                PreferenceProvider.getString(PLATFORM_KEY)
            )

            if (currentPlatform == PlatformType.OPTIONS_MODE) {
                findNavController().navigate(R.id.navigateToOpTrading)
            } else {
                PreferenceProvider.put(PLATFORM_KEY, PlatformType.OPTIONS_MODE.key)

                val navHostFragment = parentFragment as Fragment
                changeTabNavigationGraph(
                    navHostFragment.parentFragmentManager,
                    NavigationBarTabType.TRADING,
                    R.navigation.navigation_tab_trading_op,
                    R.id.tab_navigation_container
                )
            }
        }

        view.findViewById<Button>(R.id.select_fx_asset_button).setOnClickListener {
            val currentPlatform = PlatformType.forType(
                PreferenceProvider.getString(PLATFORM_KEY)
            )

            if (currentPlatform == PlatformType.FOREX_MODE) {
                findNavController().navigate(R.id.navigateToFxTrading)
            } else {
                PreferenceProvider.put(PLATFORM_KEY, PlatformType.FOREX_MODE.key)

                val navHostFragment = parentFragment as Fragment
                changeTabNavigationGraph(
                    navHostFragment.parentFragmentManager,
                    NavigationBarTabType.TRADING,
                    R.navigation.navigation_tab_trading_fx,
                    R.id.tab_navigation_container
                )
            }
        }

        return view
    }
}