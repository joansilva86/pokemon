package com.example.poke1.Presente.NewAccount

import com.example.poke1.Presente.Base.BasePresenter

class NewAccountPresenter : BasePresenter {

    var view: NewAccountView? = null
    override fun detach() {
        view = null
    }

    fun attach(view: NewAccountView) {
        this.view = view
    }

}

