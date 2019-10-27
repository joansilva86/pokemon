package com.example.poke1.Presente.ForgetPass

interface ForgetPassView {
    fun showError(srting: String)
    fun recoverPassOk()
    fun mailEmpty()
    fun showDelay(state: Boolean)
}