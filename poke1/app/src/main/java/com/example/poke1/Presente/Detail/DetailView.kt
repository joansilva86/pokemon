package com.example.poke1.Presente.Detail

interface DetailView {
    fun showError()
    fun showPokemonDetail(pokemon: PokemonDetail)
}