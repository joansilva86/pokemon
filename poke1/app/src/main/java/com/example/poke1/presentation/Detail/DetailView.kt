package com.example.poke1.presentation.Detail

interface DetailView {
    fun showError()
    fun showPokemonDetail(pokemon: PokemonDetail)
}