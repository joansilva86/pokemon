package com.example.poke1.presentation.login.login

import android.app.Activity

import android.util.Log

import android.util.Patterns
import com.example.poke1.domain.loginInteractor.FirebaseLoginExcepion
import com.example.poke1.domain.loginInteractor.LoginInteractor
import com.example.poke1.domain.loginInteractor.LoginInteractorI
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class LoginPresenter : CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

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
        try {
            launch {
                interactor.signIn(model)
                if (view != null) {
                    view?.userOk()

                }
            }
        } catch (ex: FirebaseLoginExcepion) {
            if (view != null) {
                view?.showError(ex.toString())
                view?.hideDelay()
            }
        }

    }

    fun loginWithGoogle(acct: GoogleSignInAccount) {

        interactor.signInGoogle(acct, object : LoginInteractorI.SignInGoogleCallBack {
            override fun signInGoogleOk() {
                view?.userOk()
                view?.hideDelay()
            }

            override fun signInGoogleFail() {
                Log.w(TAG, " fail to signIn")
                view?.hideDelay()
            }
        })


    }
}



