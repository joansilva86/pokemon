package com.example.poke1.presentation.Main

interface MainView {
    fun showError()
    fun showList(list: ArrayList<Pokemon>)
}