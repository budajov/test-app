package com.smallpdf.testapp.helper

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageLoader {
    private var context: Context? = null
    /**
     * Initializing ImageLoader
     */
    fun init(context: Context?) {
        this.context = context
    }

    fun loadImage(url: String?, imageView: ImageView?) {
        Picasso
            .get()
            .load(url)
            .into(imageView)
    }

    companion object {
        @JvmStatic
        @get:Synchronized
        var instance: ImageLoader? = null
            get() {
                if (field == null) {
                    field = ImageLoader()
                }
                return field
            }
            private set
    }
}