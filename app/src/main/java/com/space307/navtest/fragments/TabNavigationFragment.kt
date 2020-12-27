package com.space307.navtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.space307.navtest.R
import com.space307.navtest.data.NavigationBarTabType
import com.space307.navtest.data.PlatformType
import com.space307.navtest.utils.PreferenceProvider
import com.space307.navtest.utils.PreferenceProvider.PLATFORM_KEY
import com.space307.navtest.utils.setupWithNavController
import com.space307.navtest.views.NavigationBarView

class TabNavigationFragment : Fragment() {

    private lateinit var navigationBar: NavigationBarView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tab_navigation, container, false)

        navigationBar = view.findViewById(R.id.tab_navigation_bar_view)
        setupNavigationBar(navigationBar)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val navHostFragment = childFragmentManager.findFragmentById(
                    R.id.tab_navigation_container
                ) as NavHostFragment

                val controller = navHostFragment.navController

                var isBackHandled = controller.navigateUp()
                if (!isBackHandled) {
                    isBackHandled = if (childFragmentManager.backStackEntryCount > 0) {
                        childFragmentManager.popBackStack()
                        true
                    } else {
                        false
                    }
                }
                if (!isBackHandled) {
                    requireActivity().finish()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    fun setSelectedNavigationTab(tab: NavigationBarTabType) {
        navigationBar.selectedTab = tab
    }

    private fun setupNavigationBar(navigationBar: NavigationBarView) {
        val currentPlatformString = PreferenceProvider.getString(PLATFORM_KEY)
        val currentPlatform = if (currentPlatformString.isNotEmpty())
            PlatformType.forType(currentPlatformString)
        else {
            PreferenceProvider.put(PLATFORM_KEY, PlatformType.OPTIONS_MODE.key)
            PlatformType.OPTIONS_MODE
        }

        val tradingNavGraph = when (currentPlatform) {
            PlatformType.OPTIONS_MODE -> R.navigation.navigation_tab_trading_op
            PlatformType.FOREX_MODE -> R.navigation.navigation_tab_trading_fx
        }

        val navGraphsMap = mapOf(
            NavigationBarTabType.TRADING to tradingNavGraph,
            NavigationBarTabType.HELP to R.navigation.navigation_tab_help,
            NavigationBarTabType.DEALS to R.navigation.navigation_tab_deals_list,
            NavigationBarTabType.MARKETPLACE to R.navigation.navigation_tab_marketplace,
            NavigationBarTabType.MORE to R.navigation.navigation_tab_more
        )

        val controllerLiveData = navigationBar.setupWithNavController(
            tabsToGraphIdsMap = navGraphsMap,
            fragmentManager = childFragmentManager,
            containerId = R.id.tab_navigation_container,
            intent = requireActivity().intent
        )

        controllerLiveData.observe(requireActivity(), { selectedTab ->
            navigationBar.selectedTab = selectedTab
        })
    }
}