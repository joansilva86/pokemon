package com.example.poke1.presentation.login.forgetPass

interface ForgetPassView {
    fun showError(srting: String)
    fun recoverPassOk()
    fun mailEmpty()
    fun invalidFormatEmail()
    fun showDelay(state: Boolean)
}