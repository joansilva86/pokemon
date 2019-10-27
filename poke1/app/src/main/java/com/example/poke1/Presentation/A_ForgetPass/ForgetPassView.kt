package com.example.poke1.Presentation.A_ForgetPass

interface ForgetPassView {
    fun showError(srting: String)
    fun recoverPassOk()
    fun mailEmpty()
    fun showDelay(state: Boolean)
}