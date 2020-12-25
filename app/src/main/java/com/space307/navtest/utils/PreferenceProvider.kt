package com.space307.navtest.utils

import android.content.Context
import android.content.SharedPreferences
import java.util.HashSet

object PreferenceProvider {

    const val PLATFORM_KEY = "d4100487-1c99-4da9-9108-eed6a772217e"

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("preference", Context.MODE_PRIVATE)
    }

    fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    fun getLong(key: String): Long {
        return sharedPreferences.getLong(key, 0L)
    }

    fun getLong(key: String, defaultValue: Long): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

    fun getFloat(key: String): Float {
        return sharedPreferences.getFloat(key, 0f)
    }

    fun getFloat(key: String, defaultValue: Float): Float {
        return sharedPreferences.getFloat(key, defaultValue)
    }

    fun getDouble(key: String): Double {
        return java.lang.Double.longBitsToDouble(sharedPreferences.getLong(key, 0L))
    }

    fun getDouble(key: String, defaultValue: Double): Double {
        return java.lang.Double.longBitsToDouble(
            sharedPreferences.getLong(
                key,
                java.lang.Double.doubleToLongBits(defaultValue)
            )
        )
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun getString(key: String): String {
        return sharedPreferences.getString(key, "")!!
    }

    fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue)!!
    }

    fun getStringSet(key: String): Set<String> {
        return sharedPreferences.getStringSet(key, HashSet())!!
    }

    fun getStringSet(key: String, defaultSet: Set<String>): Set<String> {
        return sharedPreferences.getStringSet(key, defaultSet)!!
    }

    fun put(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun put(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    fun put(key: String, value: Float) {
        sharedPreferences.edit().putFloat(key, value).apply()
    }

    fun put(key: String, value: Double) {
        sharedPreferences.edit().putLong(key, java.lang.Double.doubleToRawLongBits(value)).apply()
    }

    fun put(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun put(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun put(key: String, stringSet: Set<String>) {
        sharedPreferences.edit().putStringSet(key, stringSet).apply()
    }

    fun remove(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

    fun containsKey(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

}