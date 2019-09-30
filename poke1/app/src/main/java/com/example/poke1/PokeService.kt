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
                list[i].image,
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

    fun getImagenesPokemones(
        voley: VolleyS,
        listener: ListenerImagenesPokemon,
        listenerError: ListenerErrorImagenes
        //,list: ArrayList<Pokemon>
    ) {
        for (i in 0 until list.size) {
            var url = list[i].url
            getPokemonImagen(voley, listenerPokemonImagenOk, listenerPokemonImagenError, i)
        }
        //listener.retorno(list)

    }

    fun getPokemones(
        voley: VolleyS,
        listener: ListenerPokeServiceOk,
        listenerError: ListenerPokeServiceError
    ) {
        var url = "https://pokeapi.co/api/v2/pokemon/?offset=10&limit=10"
        list.clear()
        vo = voley

        var requestObject = JsonObjectRequest(url, null, Response.Listener { response ->
            var jsonArray = response.getJSONArray("results")
            for (i in 0 until jsonArray.length()) {
                var protoPoke = jsonArray.getJSONObject(i)

                var anyPoke = Pokemon()
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

    fun getPokemonImagen(
        voley: VolleyS,
        listener: ListenerPokemonImagenOk,
        listenerError: ListenerPokemonImagenError,
        index: Int
    ) {
        var url = "https://pokeapi.co/api/v2/pokemon/${index + 1}"
        var imag = ""
        var requestObject = JsonObjectRequest(url, null, Response.Listener { response ->
            var jsonObject = response.getJSONObject("sprites")
            if (jsonObject.has("front_default"))
                imag = jsonObject.getString("front_default")
            list[index].image = imag
            listener.retorno()

        }
            , Response.ErrorListener { error ->
                var un = error
                listenerError.retorno()
            })
        voley.queue.add(requestObject)
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