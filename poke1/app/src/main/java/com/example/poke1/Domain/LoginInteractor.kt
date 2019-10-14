package com.example.poke1.Domain

class LoginInteractor {
    fun SignIn(user:String,pass:String,callback:SignInCallBack){

        callback.authenticationOk()
    }
    interface SignInCallBack{
        fun authenticationOk()
        fun authenticationError()

    }
}