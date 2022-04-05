package com.vkochenkov.wordlegame.sharedprefs

import android.content.Context

private const val LENGTH_SHARED_PREFS_STORAGE = "LENGTH_SHARED_PREFS_STORAGE"
private const val CURRENT_LENGTH_KEY = "CURRENT_LENGTH_KEY"
private const val defaultLength = 5

class LengthSharedPrefs(
    private val context: Context
) {
    private val preferences =
        context.getSharedPreferences(LENGTH_SHARED_PREFS_STORAGE, Context.MODE_PRIVATE)

    fun getLength(): Int {
        return preferences.getInt(CURRENT_LENGTH_KEY, defaultLength)
    }

    fun setLength(len: Int) {
        preferences.edit().putInt(CURRENT_LENGTH_KEY, len).apply()
    }
}