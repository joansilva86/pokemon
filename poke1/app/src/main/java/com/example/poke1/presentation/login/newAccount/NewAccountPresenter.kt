package com.example.poke1.presentation.login.newAccount

import android.app.Activity
import android.util.Log
import android.util.Patterns
import com.example.poke1.domain.loginInteractor.LoginInteractor
import com.example.poke1.domain.loginInteractor.LoginInteractorI
import com.example.poke1.presentation.Base.BasePresenter

class NewAccountPresenter (val interactor: LoginInteractor): BasePresenter {

    var view: NewAccountView? = null
    var activity: Activity? = null

    companion object {
        private const val TAG = "NewAccount"
    }

    override fun detach() {
        view = null
    }

    fun attach(view: NewAccountView, activity: Activity) {
        this.view = view
        this.activity = activity
    }

    private fun validarEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    fun createUser(model: NewAccountModel) {
        if (model.mail.isEmpty()) {
            view?.mailEmpty()
        }
        if (model.name.isEmpty()) {
            view?.nameEmpty()
        }
        if (model.lastName.isEmpty()) {
            view?.lastNameEmpty()
        }

        if (model.pass.isEmpty()) {
            view?.passEmpty()
        }
        if (model.pass2.isEmpty()) {
            view?.pass2Empty()
        }

        if (!validarEmail(model.mail)) {
            view?.invalidFormatEmail()
        }

        if (model.pass != model.pass2) {
            view?.showDiferentPass()
        }
        if (!model.isValid) {
            return
        }
        /*********************/
        //Erick te podras fijar esto
        /*********************/
        view?.showDelay()
        interactor.createUser(model,object : LoginInteractorI.CreateUserCallBack{
            override fun createUserOk() {
                view?.showUserOk()
                view?.hideDelay()
            }

            override fun createUserFail(ex: Exception) {
                Log.w(TAG, " fail to signIn", ex)
                view?.showUserFail(ex.toString())
                view?.hideDelay()
            }
        })



    }



}

