package com.example.poke1.Presente.Login

import android.app.Activity
import com.example.poke1.Domain.LoginInteractor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginPresenter {

    var db = FirebaseDatabase.getInstance()
    var auth = FirebaseAuth.getInstance()
    var dbReference: DatabaseReference

    init {

        dbReference = db.reference.child("User")
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

    fun login(user: String, pass: String) {
        var bPass = false
        var bMail = false
        if (view != null) {
            if (pass.isEmpty()) {
                view?.passEmpty()
            } else {
                bMail = true
            }
            if (user.isEmpty()) {
                view?.userEmpty()
            } else {
                bPass = true
            }
            if (bPass && bMail) {
                view?.showDelay()

                auth.signInWithEmailAndPassword(user, pass)
                    .addOnCompleteListener(activity!!) { task ->
                        if (task.isComplete && task.isSuccessful) {
                            view?.userOk()
                        } else {
                            view?.showError()
                        }
                    }

                //interactor.SignIn(user,pass,callback)

            }
            return

        }
    }

    val callback = object : LoginInteractor.SignInCallBack {
        override fun authenticationOk() {
            view?.userOk()
        }

        override fun authenticationError() {
            view?.showError()
        }

    }
}

