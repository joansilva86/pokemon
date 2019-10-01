package com.example.poke1.Service

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.Volley

class VolleyS private constructor() {
    lateinit var queue: RequestQueue
    var imageLoader: ImageLoader? = null

    companion object {
        private var instance: VolleyS? = null

        fun getInstance(context: Context): VolleyS {
            if (instance == null) {
                instance =
                    VolleyS()
                instance!!.queue = Volley.newRequestQueue(context)
                instance!!.imageLoader =
                    ImageLoader(instance!!.queue, object : ImageLoader.ImageCache {
                        private val mCache = LruCache<String, Bitmap>(10)
                        override fun putBitmap(url: String, bitmap: Bitmap?) {
                            mCache.put(url, bitmap)
                        }

                        override fun getBitmap(url: String): Bitmap? {
                            return mCache.get(url)
                        }
                    })

            }
            return instance as VolleyS
        }
    }
}