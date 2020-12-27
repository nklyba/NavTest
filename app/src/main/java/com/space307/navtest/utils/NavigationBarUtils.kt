package com.space307.navtest.utils

import android.content.Intent
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.space307.navtest.R
import com.space307.navtest.data.NavigationBarTabType
import com.space307.navtest.views.NavigationBarView

private val FIRST_TAB = NavigationBarTabType.TRADING

/**
 * Manages the various graphs needed for a [NavigationBarView].
 *
 * This sample is a workaround until the Navigation Component supports multiple back stacks.
 */
fun NavigationBarView.setupWithNavController(
    tabsToGraphIdsMap: Map<NavigationBarTabType, Int>,
    fragmentManager: FragmentManager,
    containerId: Int,
    intent: Intent
): LiveData<NavigationBarTabType> {

    val tabsToTagMap = mutableMapOf<NavigationBarTabType, String>()
    var selectedNavController: NavController? = null

    // Result. Mutable live data with the selected controlled
    val selectedNavigationTab = MutableLiveData<NavigationBarTabType>()

    // First create a NavHostFragment for each NavGraph ID
    tabsToGraphIdsMap.forEach { (tab, navGraphId) ->
        val fragmentTag = getFragmentTag(tab)

        // Find or create the Navigation host fragment
        val navHostFragment = obtainNavHostFragment(
            fragmentManager,
            fragmentTag,
            navGraphId,
            containerId
        )

        // Save to the map
        tabsToTagMap[tab] = fragmentTag

        // Attach or detach nav host fragment depending on whether it's the selected tab.
        if (this.selectedTab == tab) {
            // Update livedata with the selected tab
            selectedNavigationTab.value = tab
            selectedNavController = navHostFragment.navController
            attachNavHostFragment(fragmentManager, navHostFragment, tab == FIRST_TAB)
        } else {
            detachNavHostFragment(fragmentManager, navHostFragment)
        }
    }

    // Now connect selecting an tab with swapping Fragments
    var selectedItemTag = tabsToTagMap[this.selectedTab]
    val firstFragmentTag = getFragmentTag(FIRST_TAB)
    var isOnFirstFragment = selectedItemTag == firstFragmentTag

    // When a navigation tab is selected
    setTabSelectedListener { selectedTab ->
        // Don't do anything if the state is state has already been saved.
        if (!fragmentManager.isStateSaved) {
            val newlySelectedItemTag = tabsToTagMap[selectedTab]
            if (selectedItemTag != newlySelectedItemTag) {
                // Pop everything above the first fragment (the "fixed start destination")
                fragmentManager.popBackStack(
                    firstFragmentTag,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
                val selectedFragment = fragmentManager.findFragmentByTag(newlySelectedItemTag)
                        as NavHostFragment

                // Exclude the first fragment tag because it's always in the back stack.
                if (firstFragmentTag != newlySelectedItemTag) {
                    // Commit a transaction that cleans the back stack and adds the first fragment
                    // to it, creating the fixed started destination.
                    fragmentManager.beginTransaction()
                        .setCustomAnimations(
                            R.anim.nav_default_enter_anim,
                            R.anim.nav_default_exit_anim,
                            R.anim.nav_default_pop_enter_anim,
                            R.anim.nav_default_pop_exit_anim
                        )
                        .attach(selectedFragment)
                        .setPrimaryNavigationFragment(selectedFragment)
                        .apply {
                            // Detach all other Fragments
                            tabsToTagMap.values.forEach { fragmentTag ->
                                if (fragmentTag != newlySelectedItemTag) {
                                    detach(fragmentManager.findFragmentByTag(firstFragmentTag)!!)
                                }
                            }
                        }
                        .addToBackStack(firstFragmentTag)
                        .setReorderingAllowed(true)
                        .commit()
                }
                selectedItemTag = newlySelectedItemTag
                isOnFirstFragment = selectedItemTag == firstFragmentTag
                selectedNavController = selectedFragment.navController
                selectedNavigationTab.value = selectedTab
            }
        }
    }

    // Handle deep link
    setupDeepLinks(tabsToGraphIdsMap, fragmentManager, containerId, intent)

    // Finally, ensure that we update our BottomNavigationView when the back stack changes
    fragmentManager.addOnBackStackChangedListener {
        if (!isOnFirstFragment && !fragmentManager.isOnBackStack(firstFragmentTag)) {
            this.selectedTab = FIRST_TAB
        }

        // Reset the graph if the currentDestination is not valid (happens when the back
        // stack is popped after using the back button).
        selectedNavController?.let { controller ->
            if (controller.currentDestination == null) {
                controller.navigate(controller.graph.id)
            }
        }
    }
    return selectedNavigationTab
}

fun changeTabNavigationGraph(
    fragmentManager: FragmentManager,
    tab: NavigationBarTabType,
    navGraphId: Int,
    containerId: Int
) {
    val fragmentTag = getFragmentTag(tab)

    if (tab == FIRST_TAB && fragmentManager.isOnBackStack(fragmentTag)) {
        fragmentManager.popBackStackImmediate()
    }

    val navHostFragment = NavHostFragment.create(navGraphId)
    fragmentManager.beginTransaction()
        .replace(containerId, navHostFragment, fragmentTag)
        .commitNow()

    attachNavHostFragment(fragmentManager, navHostFragment, tab == FIRST_TAB)
}

private fun NavigationBarView.setupDeepLinks(
    tabsToGraphIdsMap: Map<NavigationBarTabType, Int>,
    fragmentManager: FragmentManager,
    containerId: Int,
    intent: Intent
) {
    tabsToGraphIdsMap.forEach { (tab, navGraphId) ->
        val fragmentTag = getFragmentTag(tab)

        // Find or create the Navigation host fragment
        val navHostFragment = obtainNavHostFragment(
            fragmentManager,
            fragmentTag,
            navGraphId,
            containerId
        )
        // Handle Intent
        if (navHostFragment.navController.handleDeepLink(intent) && selectedTab != tab) {
            this.selectedTab = tab
        }
    }
}

private fun detachNavHostFragment(
    fragmentManager: FragmentManager,
    navHostFragment: NavHostFragment
) {
    fragmentManager.beginTransaction()
        .detach(navHostFragment)
        .commitNow()
}

private fun attachNavHostFragment(
    fragmentManager: FragmentManager,
    navHostFragment: NavHostFragment,
    isPrimaryNavFragment: Boolean
) {
    fragmentManager.beginTransaction()
        .attach(navHostFragment)
        .apply {
            if (isPrimaryNavFragment) {
                setPrimaryNavigationFragment(navHostFragment)
            }
        }
        .commitNow()
}

private fun obtainNavHostFragment(
    fragmentManager: FragmentManager,
    fragmentTag: String,
    navGraphId: Int,
    containerId: Int
): NavHostFragment {
    // If the Nav Host fragment exists, return it
    val existingFragment = fragmentManager.findFragmentByTag(fragmentTag) as NavHostFragment?
    existingFragment?.let { return it }

    // Otherwise, create it and return it.
    val navHostFragment = NavHostFragment.create(navGraphId)
    fragmentManager.beginTransaction()
        .add(containerId, navHostFragment, fragmentTag)
        .commitNow()
    return navHostFragment
}

private fun FragmentManager.isOnBackStack(backStackName: String): Boolean {
    val backStackCount = backStackEntryCount
    for (index in 0 until backStackCount) {
        if (getBackStackEntryAt(index).name == backStackName) {
            return true
        }
    }
    return false
}

private fun getFragmentTag(tab: NavigationBarTabType) = "bottomNavigation#${tab.ordinal}"
