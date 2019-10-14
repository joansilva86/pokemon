package com.example.poke1.Presente.ForgetPass

import com.example.poke1.Presente.Base.BasePresenter

class ForgetPassPresenter : BasePresenter {

    var view: ForgetPassView? = null
    override fun detach() {
        this.view = null
    }

    fun attach(view: ForgetPassView) {
        this.view = view
    }
}