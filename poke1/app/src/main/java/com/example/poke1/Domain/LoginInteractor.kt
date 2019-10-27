package com.example.poke1.Domain

import com.example.poke1.Presentation.A_ForgetPass.ForgetPassModel
import com.example.poke1.Presentation.A_Login.LoginModel
import com.example.poke1.Presentation.A_NewAccount.NewAccountModel

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginInteractor {

    var auth = FirebaseAuth.getInstance()
    var db = FirebaseDatabase.getInstance()
    var dbReference: DatabaseReference
    init {

        dbReference = db.reference.child("User")
    }

    fun sendPasswordResetEmail(
        forgetPassModel: ForgetPassModel,
        callback: SendPasswordResetEmailCallBack
    ) {
        auth.sendPasswordResetEmail(forgetPassModel.mail)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback.sendOk()
                } else {
                    callback.sendFail(task.exception.toString())
                }
            }
    }

    fun signIn(model: LoginModel, callback: SignInCallBack) {

        auth.signInWithEmailAndPassword(model.mail, model.pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback.authenticationOk()
                } else {
                    callback.authenticationError(task.exception!!)
                }
            }
    }

    fun createUser(model: NewAccountModel, callback: CreateUserCallBack) {
        auth.createUserWithEmailAndPassword(model.mail, model.pass)
            .addOnCompleteListener { task ->
                if (task.isComplete && task.isSuccessful) {
                    sendEmailVerification()
                    val user = auth.currentUser
                    val userDB = dbReference.child(user?.uid)
                    userDB.child("Name").setValue(model.name)
                    userDB.child("LasName").setValue(model.lastName)

                    callback.createUserOk()
                } else {
                    callback.createUserFail(task.exception!!)
                }

            }
    }

    private fun sendEmailVerification() {
        val user = auth.currentUser
        user?.sendEmailVerification()
            ?.addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    /******************/
                    /*ACA NO SE BIEN QUE HACER??*/
                    /******************/
                } else {
                    //Log.e(TAG, "sendEmailVerification", task.exception)

                }

            }

    }

    interface CreateUserCallBack {
        fun createUserOk()
        fun createUserFail(ex: Exception)
    }

    interface SendPasswordResetEmailCallBack {
        fun sendOk()
        fun sendFail(msj: String)

    }

    interface SignInCallBack {
        fun authenticationOk()
        fun authenticationError(string: Exception)
    }
}