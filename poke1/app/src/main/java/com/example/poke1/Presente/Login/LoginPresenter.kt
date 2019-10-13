package com.example.poke1.Presente.Login

import com.example.poke1.Domain.LoginInteractor

class LoginPresenter {
    val interactor = LoginInteractor()
    var view: LoginView? = null

    fun attach(view: LoginView) {
        this.view = view
    }

    fun detach() {
        this.view = null
    }

    fun login(user: String, pass: String) {
        var bPass = false
        var bMail = false
        if (view != null) {
            if (pass.isEmpty()) {
                view?.passEmpty()
            } else {
                bMail = true
            }
            if (user.isEmpty()) {
                view?.userEmpty()
            } else {
                bPass = true
            }
            if (bPass && bMail) {
                view?.showDelay()
                interactor.SignIn(user,pass)
            }
            return

        }
    }
}

