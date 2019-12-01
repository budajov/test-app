package com.smallpdf.testapp.data

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesProvider private constructor() {
    private var prefs: SharedPreferences? = null
    /**
     * Initializing shared preferences
     */
    fun init(context: Context) {
        prefs = context.getSharedPreferences(
            SHARED_PREFERENCES_NAME_USER_LEVEL,
            Context.MODE_PRIVATE
        )
    }

    /**
     * Clears all data from shared preferences
     */
    fun clear() {
        prefs!!.edit().clear().apply()
    }

    fun setExample(exampleString: String?) {
        prefs!!.edit()
            .putString(EXAMPLE_KEY, exampleString)
            .apply()
    }

    val example: Int
        get() = prefs!!.getInt(EXAMPLE_KEY, 60)

    companion object {
        private const val SHARED_PREFERENCES_NAME_USER_LEVEL = "user_info"
        private const val EXAMPLE_KEY = "example"
        @get:Synchronized
        var instance: SharedPreferencesProvider? = null
            get() {
                if (field == null) {
                    field = SharedPreferencesProvider()
                }
                return field
            }
            private set
    }
}