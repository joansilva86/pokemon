package com.example.poke1

import android.graphics.Bitmap
import org.json.JSONObject

data class Pokemon(
    var namePoke: String = "", var image: String = "", var url: String = ""
) {
    var imagenBitmap : Bitmap? = null
    fun fillWith(json: JSONObject) {
        if (json.has("name"))
            this.namePoke = json.getString("name")
        if (json.has("url"))
            this.url = json.getString("url")
    }
}