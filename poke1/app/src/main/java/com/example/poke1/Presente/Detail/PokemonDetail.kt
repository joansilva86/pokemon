package com.example.poke1.Presente.Detail

import android.graphics.Bitmap
import org.json.JSONObject

data class PokemonDetail (var id : Int = 0,
    var nombre :String = "f",var tipo : String = "",
                          var frontUrl :String = "",var backUrl :String = "" ){
    var imageFront : Bitmap? = null
    var imageBack : Bitmap? = null
    fun fillWith(jsonObject: JSONObject){
        if(jsonObject.has("name"))
            nombre = jsonObject.getString("name")
        if(jsonObject.has("sprites")) {
            frontUrl = jsonObject.getJSONObject("sprites").getString("front_default")
            backUrl = jsonObject.getJSONObject("sprites").getString("back_default")
        }
    }
}