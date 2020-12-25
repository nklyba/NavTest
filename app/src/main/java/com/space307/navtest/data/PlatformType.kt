package com.space307.navtest.data

enum class PlatformType(val key: String) {
    OPTIONS_MODE("ftt"),
    FOREX_MODE("fx");

    companion object {
        fun forType(text: String) = values().find { it.key == text }!!
    }
}