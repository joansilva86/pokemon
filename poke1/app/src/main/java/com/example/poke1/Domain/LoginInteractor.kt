package com.example.poke1.Domain

import android.util.Log
import com.example.poke1.Presente.ForgetPass.ForgetPassModel

import com.google.firebase.auth.FirebaseAuth

class LoginInteractor {

    var auth = FirebaseAuth.getInstance()

    companion object {
        private const val TAG = "ForgetPass"
    }

    fun sendPasswordResetEmail(forgetPassModel: ForgetPassModel,callback :SendPasswordResetEmailCallBack){
        auth.sendPasswordResetEmail(forgetPassModel.mail)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    callback.sendOk()
                } else {
                    Log.w(TAG, "no se pudo enviar mail de recupero", task.exception)
                    callback.sendFail(task.exception.toString())
                }
            }
    }

    fun SignIn(user:String,pass:String,callback:SignInCallBack){

        callback.authenticationOk()
    }
    interface SendPasswordResetEmailCallBack{
        fun sendOk()
        fun sendFail(msj: String)

    }

    interface SignInCallBack{
        fun authenticationOk()
        fun authenticationError()

    }
}