package com.example.poke1.presentation.login.forgetPass

import android.app.Activity
import com.example.poke1.domain.loginInteractor.FirebaseForgetPassException

import com.example.poke1.domain.loginInteractor.LoginInteractor
import com.example.poke1.domain.loginInteractor.LoginInteractorI
import com.example.poke1.presentation.base.BasePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class ForgetPassPresenter (val interactor: LoginInteractor): BasePresenter , CoroutineScope{


    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


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
            return
        }
        if (!model.isValid) {
            view?.invalidFormatEmail()
            return
        }

        view?.showDelay(true)
        launch{
            try {
                interactor.sendPasswordResetEmail(model)
                view?.recoverPassOk()
            }catch (ex: FirebaseForgetPassException){
                view?.showError(ex.message.toString())
            }
        }
        view?.showDelay(false)
    }
}