package com.example.poke1.Presentation.Detail

interface DetailView {
    fun showError()
    fun showPokemonDetail(pokemon: PokemonDetail)
}