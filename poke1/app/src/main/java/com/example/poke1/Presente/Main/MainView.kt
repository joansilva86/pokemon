package com.example.poke1.Presente.Main

interface MainView {
    fun showError()
    fun showList(list: ArrayList<Pokemon>)
}