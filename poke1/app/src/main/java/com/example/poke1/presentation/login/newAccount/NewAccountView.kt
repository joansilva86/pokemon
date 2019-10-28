package com.example.poke1.presentation.login.newAccount

interface NewAccountView  {

    fun mailEmpty()
    fun passEmpty()
    fun pass2Empty()
    fun nameEmpty()
    fun lastNameEmpty()
    fun showDiferentPass()
    fun showUserOk()
    fun showUserFail(msj:String)
    fun showDelay()
    fun hideDelay()
    fun invalidFormatEmail()
    fun sendVerificationEmail(state:Boolean)
}
