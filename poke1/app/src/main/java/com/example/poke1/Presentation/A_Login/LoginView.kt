package com.example.poke1.Presentation.A_Login

interface LoginView {
    fun showError(msj: String)
    fun showDelay(state: Boolean)
    fun showCreateAcount()
    fun userOk()
    fun userEmpty()
    fun passEmpty()
    fun invalidFormatEmail()

}