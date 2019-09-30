package com.example.poke1

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleyS private constructor() {
    lateinit var queue: RequestQueue

    companion object {
        private var instance: VolleyS? = null

        fun getInstance(context: Context): VolleyS {
            if (instance == null) {
                instance = VolleyS()
                instance!!.queue = Volley.newRequestQueue(context)
            }
            return instance as VolleyS
        }
    }
}