package com.example.poke1.Presente.Login

interface LoginView {
    fun showError(msj: String)
    fun showDelay(state: Boolean)
    fun showCreateAcount()
    fun userOk()
    fun userEmpty()
    fun passEmpty()
    fun invalidFormatEmail()

}