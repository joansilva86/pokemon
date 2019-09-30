package com.example.poke1

import android.graphics.Bitmap
import org.json.JSONObject

data class Pokemon(
    var namePoke: String = "", var imageUrl: String = "", var url: String = "",var id :Int = 0
) {
    var imagenBitmap : Bitmap? = null
    fun fillWith(json: JSONObject) {
        if (json.has("name"))
            this.namePoke = json.getString("name")
        if (json.has("url"))
            this.url = json.getString("url")
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/$id.png"
    }
}