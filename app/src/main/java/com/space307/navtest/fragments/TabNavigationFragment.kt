package com.space307.navtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.space307.navtest.R
import com.space307.navtest.utils.getNavigationBarTabFromGraphId
import com.space307.navtest.utils.setTradingPlatform
import com.space307.navtest.utils.setupWithNavController
import com.space307.navtest.views.NavigationBarView

class TabNavigationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tab_navigation, container, false)

        setupNavigationBar(view.findViewById(R.id.tab_navigation_bar_view))

        if (savedInstanceState == null) {
            val navHostFragment = childFragmentManager.findFragmentById(R.id.tab_navigation_container) as NavHostFragment
            val navController = navHostFragment.navController
            setTradingPlatform(navController)
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val controller = findNavController(
                    requireActivity(),
                    R.id.tab_navigation_container
                )
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

    private fun setupNavigationBar(navigationBar: NavigationBarView) {


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
    }
}