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
import com.space307.navtest.utils.changeTabNavigationGraph

class DealsListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_deals_list, container, false)

        view.findViewById<Button>(R.id.active_op_deal_button).setOnClickListener {
            val currentPlatform = PlatformType.forType(
                PreferenceProvider.getString(PreferenceProvider.PLATFORM_KEY)
            )

            if (currentPlatform != PlatformType.OPTIONS_MODE) {
                changePlatform(PlatformType.OPTIONS_MODE)
            }

            selectTradingTab()
        }

        view.findViewById<Button>(R.id.active_fx_deal_button).setOnClickListener {
            val currentPlatform = PlatformType.forType(
                PreferenceProvider.getString(PreferenceProvider.PLATFORM_KEY)
            )

            if (currentPlatform != PlatformType.FOREX_MODE) {
                changePlatform(PlatformType.FOREX_MODE)
            }

            selectTradingTab()
        }

        view.findViewById<Button>(R.id.closed_op_deal_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToOpClosedDealBottomSheet)
        }

        view.findViewById<Button>(R.id.closed_fx_deal_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToFxClosedDealBottomSheet)
        }

        return view
    }

    private fun changePlatform(platform: PlatformType) {
        PreferenceProvider.put(PreferenceProvider.PLATFORM_KEY, platform.key)

        val navGraphId = when(platform) {
            PlatformType.OPTIONS_MODE -> R.navigation.navigation_tab_trading_op
            PlatformType.FOREX_MODE -> R.navigation.navigation_tab_trading_fx
        }

        val navHostFragment = parentFragment as Fragment
        changeTabNavigationGraph(
            fragmentManager = navHostFragment.parentFragmentManager,
            tab = NavigationBarTabType.TRADING,
            navGraphId = navGraphId,
            containerId = R.id.tab_navigation_container
        )
    }

    private fun selectTradingTab() {
        val navHostFragment = parentFragment as Fragment
        val tabFragment = navHostFragment.parentFragment as TabNavigationFragment
        tabFragment.setSelectedNavigationTab(NavigationBarTabType.TRADING)
    }
}