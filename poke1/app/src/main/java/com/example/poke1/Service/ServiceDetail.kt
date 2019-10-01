package com.example.poke1.Service

import android.content.Context
import android.widget.ImageView
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.poke1.PokemonDetail

class ServiceDetail(var context: Context) {

    var pokemon = PokemonDetail()

    fun getDetailImage(listener : ListenerImage,listenerError: ListenerErrorImage) {
        var vo = VolleyS.getInstance(context)
        var imageRequest = ImageRequest(pokemon.frontUrl,
            Response.Listener { response->
                pokemon.imageFront = response
                listener.retorno(pokemon)
            },
            0, 0, ImageView.ScaleType.CENTER,null ,
            Response.ErrorListener {
                listenerError.retorno()
            })
        vo.queue.add(imageRequest)
    }

    fun getDetailPokemonId(
        id: Int,
        listener: ListenerDetailPokemon,
        listenerError: ListenerDetailPokemonError
    ) {
        var url = "https://pokeapi.co/api/v2/pokemon/$id"

        pokemon.id = id
        var vo = VolleyS.getInstance(context)
        var requestObject = JsonObjectRequest(url, null, Response.Listener { response ->

            pokemon.fillWith(response)
            listener.retorno()
        }, Response.ErrorListener { listenerError.retorno() })
        vo.queue.add(requestObject)
    }
}

interface ListenerErrorImage{
    fun retorno()
}
interface ListenerImage{
    fun retorno(pokemon: PokemonDetail)
}

interface ListenerDetailPokemon {
    fun retorno()
}

interface ListenerDetailPokemonError {
    fun retorno()
}