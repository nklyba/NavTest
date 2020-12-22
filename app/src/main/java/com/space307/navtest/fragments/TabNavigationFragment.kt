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

class TabNavigationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tab_navigation, container, false)
        val navigationBar = view.findViewById<NavigationBarView>(R.id.tab_navigation_bar_view)

        val navGraphIds = listOf(
            R.navigation.navigation_tab_trading,
            R.navigation.navigation_tab_help,
            R.navigation.navigation_tab_deals_list,
            R.navigation.navigation_tab_marketplace,
            R.navigation.navigation_tab_more
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controllerLiveData = navigationBar.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = childFragmentManager,
            containerId = R.id.tab_navigation_container,
            intent = requireActivity().intent
        )

        controllerLiveData.observe(requireActivity(), { navController ->
            navigationBar.selectedTab = getNavigationBarTabFromGraphId(navController.graph.id)
        })

        return view
    }
}