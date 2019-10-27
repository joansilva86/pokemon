package com.example.poke1.Service

import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.poke1.Presentation.Main.MainView
import com.example.poke1.Presentation.Main.Pokemon

class PokeService(private var view: MainView) {

    private var list = ArrayList<Pokemon>()
    private var vo: VolleyS? = null



    var listenerPokemonImagenError = object : ListenerPokemonImagenError {
        override fun retorno() {

        }
    }



    fun getPokemones(
        voley: VolleyS,
        listener: ListenerPokeServiceOk,
        listenerError: ListenerPokeServiceError
    ) {
        var url = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=100"
        list.clear()
        vo = voley
/*
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
        })*/

        var requestObject = JsonObjectRequest(url, null, Response.Listener { response ->
            var jsonArray = response.getJSONArray("results")
            for (i in 0 until jsonArray.length()) {
                var protoPoke = jsonArray.getJSONObject(i)

                var anyPoke = Pokemon(id = i + 1)
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