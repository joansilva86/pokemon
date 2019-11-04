package com.example.poke1.domain.loginInteractor

import com.example.poke1.presentation.login.forgetPass.ForgetPassModel
import com.example.poke1.presentation.login.login.LoginModel
import com.example.poke1.presentation.login.newAccount.NewAccountModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface LoginInteractorI {


    suspend fun signInGoogle(acct: GoogleSignInAccount)
    suspend fun sendPasswordResetEmail(forgetPassModel: ForgetPassModel)
    suspend fun signIn(model: LoginModel)
    suspend fun createUser(model: NewAccountModel)

    interface SignInGoogleCallBack{
        fun signInGoogleOk()
        fun signInGoogleFail()
    }
}