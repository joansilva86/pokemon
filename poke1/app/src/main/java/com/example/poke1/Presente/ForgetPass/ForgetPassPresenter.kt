package com.example.poke1.Presente.ForgetPass

import android.app.Activity
import android.util.Log
import com.example.poke1.Domain.LoginInteractor
import com.example.poke1.Presente.Base.BasePresenter

class ForgetPassPresenter (val interactor: LoginInteractor): BasePresenter {


    companion object {
        private const val TAG = "ForgetPass"
    }


    var view: ForgetPassView? = null
    var activity: Activity? = null

    override fun detach() {
        this.view = null
        this.activity = null
    }

    fun attach(view: ForgetPassView, activity: Activity) {
        this.view = view
        this.activity = activity
    }

    fun forgetPass(model: ForgetPassModel) {
        if (model.mail.isEmpty()) {
            view?.mailEmpty()
        }
        if (!model.isValid) {
            return
        }
        view?.showDelay(true)
        interactor.sendPasswordResetEmail(model, object :  LoginInteractor.SendPasswordResetEmailCallBack {
            override fun sendFail(msj: String) {
                view?.showError(msj)
            }

            override fun sendOk() {
                view?.recoverPassOk()
            }
        })


        view?.showDelay(false)
    }
}