package com.example.poke1.domain.loginInteractor

import com.example.poke1.presentation.login.forgetPass.ForgetPassModel
import com.example.poke1.presentation.login.login.LoginModel
import com.example.poke1.presentation.login.newAccount.NewAccountModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface LoginInteractorI {


    fun signInGoogle(acct: GoogleSignInAccount, callback: SignInGoogleCallBack)
    fun sendPasswordResetEmail(
        forgetPassModel: ForgetPassModel,
        callback: SendPasswordResetEmailCallBack
    )
    suspend fun signIn(model: LoginModel)
    fun createUser(model: NewAccountModel, callback: CreateUserCallBack)

    interface CreateUserCallBack {
        fun createUserOk()
        fun createUserFail(ex: Exception)
    }

    interface SendPasswordResetEmailCallBack {
        fun sendOk()
        fun sendFail(msj: String)

    }

    /*interface SignInCallBack {
        fun authenticationOk()
        fun authenticationError(string: Exception)
    }*/

    interface SignInGoogleCallBack{
        fun signInGoogleOk()
        fun signInGoogleFail()
    }
}