package com.example.poke1

import android.graphics.Bitmap
import android.widget.ImageView
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONArray

class PokeService(private var view: MainView) {
    //private var url = "https://pokeapi.co/api/v2/type/3/"
    //private var url = "https://pokeapi.co/api/v2/pokemon/"

    private var list = ArrayList<Pokemon>()
    private var cont = 0
    private var vo: VolleyS? = null

    var listenerPokemonImagenOk = object : ListenerPokemonImagenOk {

        override fun retorno() {
            cont++
            if (cont > 8)
                getBitMap()
        }
    }

    var listenerPokemonImagenError = object : ListenerPokemonImagenError {
        override fun retorno() {

        }
    }

    fun getBitMap() {
        cont = 0
        for (i in 0 until list.size) {
            var imageRequest = ImageRequest(
                list[i].imageUrl,
                Response.Listener<Bitmap> {
                    list[i].imagenBitmap = it
                    cont++
                    if (cont > 8)
                        view!!.showList(list)
                },
                0,
                0,
                ImageView.ScaleType.CENTER,
                null,
                Response.ErrorListener {
                    view.showError()
                })
            vo!!.queue.add(imageRequest)
        }
    }

    fun getPokemones(
        voley: VolleyS,
        listener: ListenerPokeServiceOk,
        listenerError: ListenerPokeServiceError
    ) {
        var url = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=10"
        list.clear()
        vo = voley

        var requestObject = JsonObjectRequest(url, null, Response.Listener { response ->
            var jsonArray = response.getJSONArray("results")
            for (i in 0 until jsonArray.length()) {
                var protoPoke = jsonArray.getJSONObject(i)

                var anyPoke = Pokemon(id=i+1)
                anyPoke.fillWith(protoPoke)
                list.add(anyPoke)
            }
            listener.retorno(list)
        }, Response.ErrorListener { error ->
            var un = error
            listenerError.retorno()
        })



        voley.queue.add(requestObject)
        //voley.queue.add(requestArray)
    }

}

interface ListenerPokemonImagenOk {
    fun retorno()
}

interface ListenerPokemonImagenError {
    fun retorno()
}

interface ListenerPokeServiceOk {
    fun retorno(list: ArrayList<Pokemon>)
}

interface ListenerPokeServiceError {
    fun retorno()
}

interface ListenerImagenesPokemon {
    fun retorno(list: ArrayList<Pokemon>)
}

interface ListenerErrorImagenes {
    fun retorno()
}