package com.smallpdf.testapp.helper

import android.content.Context
import android.net.ConnectivityManager

class Util {
    private var context: Context? = null
    /**
     * Initializing Util
     */
    fun init(context: Context?) {
        this.context = context
    }

    fun hasConnection(): Boolean {
        val manager =
            context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo
        // check if we have connection
        return networkInfo != null && networkInfo.isConnected
    }

    companion object {
        @JvmStatic
        @get:Synchronized
        var instance: Util? = null
            get() {
                if (field == null) {
                    field =
                        Util()
                }
                return field
            }
            private set
    }
}