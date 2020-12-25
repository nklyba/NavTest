package com.space307.navtest.utils

import androidx.navigation.NavController
import com.space307.navtest.R
import com.space307.navtest.data.PlatformType

fun setTradingPlatform(navController: NavController) {
    val currentPlatformString = PreferenceProvider.getString(PreferenceProvider.PLATFORM_KEY)
    val currentPlatform = if (currentPlatformString.isNotEmpty())
        PlatformType.forType(currentPlatformString)
    else
        PlatformType.OPTIONS_MODE

    //set start destination
    val navInflater = navController.navInflater
    val graph = navInflater.inflate(R.navigation.navigation_tab_trading)
    graph.startDestination = when (currentPlatform) {
        PlatformType.OPTIONS_MODE -> R.id.opTradingFragment
        PlatformType.FOREX_MODE -> R.id.fxTradingFragment
    }
    navController.graph = graph
}