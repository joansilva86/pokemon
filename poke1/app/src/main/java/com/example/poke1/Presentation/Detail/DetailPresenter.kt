package com.example.poke1.Presentation.Detail

import android.content.Context
import com.example.poke1.Service.*

class DetailPresenter (var context: Context){
    private var view: DetailView? = null
    var ser = ServiceDetail(context)

    fun attach(view: DetailView) {
        this.view = view

    }

    fun detach() {
        this.view = null
    }

    fun begin() {

        ser.getDetailPokemonId(2, listener,listeneError)

    }
    var listenerImage =  object : ListenerImage {
        override fun retorno(pokemon: PokemonDetail){
            view?.showPokemonDetail(pokemon)
        }
    }
    var listenerErrorImage = object : ListenerErrorImage {
        override fun retorno(){}
    }

    var listener = object : ListenerDetailPokemon {
        override fun retorno() {

            ser.getDetailImage(listenerImage,listenerErrorImage)


        }
    }
    var listeneError = object  : ListenerDetailPokemonError {
        override fun retorno(){
            view?.showError()
        }
    }
}

