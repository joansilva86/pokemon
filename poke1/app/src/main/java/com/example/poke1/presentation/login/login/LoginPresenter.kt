package com.example.poke1.presentation.login.login

import android.app.Activity

import android.util.Log

import android.util.Patterns
import com.example.poke1.domain.loginInteractor.FirebaseLoginException
import com.example.poke1.domain.loginInteractor.GoogleSignInException
import com.example.poke1.domain.loginInteractor.LoginInteractor
import com.example.poke1.domain.loginInteractor.LoginInteractorI
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class LoginPresenter @Inject constructor(private val interactor : LoginInteractorI) : CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job



    var view: LoginView? = null
    //var activity: Activity? = null

    fun attach(view: LoginView) {
        this.view = view
      //  this.activity = activity
    }

    fun detach() {
        this.view = null
        //activity = null
    }

    fun detachJob() {
        coroutineContext.cancel()
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
        view?.showDelay()
        launch {
            try {
                interactor.signIn(model)
                if (view != null) {
                    view?.userOk()

                }
            } catch (ex: FirebaseLoginException) {
                /*OSo vos sabes que pudo pasar aca
                * cuando viene por el error y pregunta por la vista esta
                * parece que esta en null pero sin embargo la vista es visible*/
                //if (view != null) {
                view?.showError(ex.toString())
                view?.hideDelay()
                //}
            }
        }
    }

    fun loginWithGoogle(acct: GoogleSignInAccount) {
        launch {
            try {
                interactor.signInGoogle(acct)
                view?.hideDelay()
                view?.userOk()

            } catch (ex: GoogleSignInException) {
                view?.showError(ex.toString())
            }
        }


    }
}



