package com.example.poke1

import android.content.Context

class MainPresenter(private var view: MainView?) {
    private var ser : PokeService
    private var vo: VolleyS? = null
    private var lista =  ArrayList<Pokemon>()

    init {
        ser = PokeService(view!!)
    }

    fun refresh() {
        view?.showList(lista)
    }

    fun attach(view: MainView) {
        this.view = view
    }

    fun detach() {
        this.view = null
    }

    fun begin(context: Context) {

        vo = VolleyS.getInstance(context)
        ser.getPokemones(vo!!, listenerOk, listenerError)


    }

    var listenerOk = object : ListenerPokeServiceOk {
        override fun retorno(list: ArrayList<Pokemon>) {
            ser.getBitMap()
            //view?.showList(list)
        }
    }

    var listenerError = object : ListenerPokeServiceError {
        override fun retorno() {
            view?.showError()
        }

    }
    var listenerErrorImagenes = object : ListenerErrorImagenes {
        override fun retorno() {

        }
    }
    var listenerImagenesPokemon = object : ListenerImagenesPokemon {
        override fun retorno(list: ArrayList<Pokemon>) {
            lista = list
            //view?.showList(list)
        }

    }
}

interface MainView {
    fun showError()
    fun showList(list: ArrayList<Pokemon>)
}