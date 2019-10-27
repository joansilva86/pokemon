package com.example.poke1.Presentation.A_Login

import android.app.Activity

import android.util.Log
import com.example.poke1.Domain.LoginInteractor
import android.util.Patterns


class LoginPresenter {

    companion object {
        private const val TAG = "LoginPresenter"
    }


    val interactor = LoginInteractor()
    var view: LoginView? = null
    var activity: Activity? = null

    fun attach(view: LoginView, activity: Activity) {
        this.view = view
        this.activity = activity
    }

    fun detach() {
        this.view = null
        activity = null
    }


    fun login(model: LoginModel) {
        val pattern = Patterns.EMAIL_ADDRESS

        if (view != null) {
            if (model.pass.isEmpty()) {
                view?.passEmpty()
            }

            if (model.mail.isEmpty()) {
                view?.userEmpty()
            }
            if (!pattern.matcher(model.mail).matches()) {
                view?.invalidFormatEmail()
            }

            if (!model.isValid) {
                return
            }
        }
        view?.showDelay(true)
        interactor.signIn(model, object : LoginInteractor.SignInCallBack {
            override fun authenticationOk() {
                view?.userOk()
                view?.showDelay(false)
            }

            override fun authenticationError(ex: Exception) {
                Log.w(TAG, " fail to signIn", ex)
                view?.showError(ex.toString())
                view?.showDelay(false)
            }
        })
    }
}



