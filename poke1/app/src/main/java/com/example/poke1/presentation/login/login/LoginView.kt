package com.example.poke1.presentation.login.login

interface LoginView {
    fun showError(msj: String)
    fun showDelay()
    fun hideDelay()
    fun showCreateAcount()
    fun userOk()
    fun userEmpty()
    fun passEmpty()
    fun invalidFormatEmail()
    fun signInGoogleOk()
    fun signInGoogleFail()
}