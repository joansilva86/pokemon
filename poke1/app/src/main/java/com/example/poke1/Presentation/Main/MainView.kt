package com.example.poke1.Presentation.Main

interface MainView {
    fun showError()
    fun showList(list: ArrayList<Pokemon>)
}