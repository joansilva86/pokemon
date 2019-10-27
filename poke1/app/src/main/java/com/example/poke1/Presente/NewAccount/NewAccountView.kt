package com.example.poke1.Presente.NewAccount

interface NewAccountView  {

    fun mailEmpty()
    fun passEmpty()
    fun pass2Empty()
    fun nameEmpty()
    fun lastNameEmpty()
    fun showDiferentPass()
    fun showUserOk()
    fun showUserFail(msj:String)
    fun showDelay(state: Boolean)
    fun invalidFormatEmail()
    fun sendVerificationEmail(state:Boolean)
}
