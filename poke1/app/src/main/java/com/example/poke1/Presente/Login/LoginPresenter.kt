package com.example.poke1.Presente.Login

import android.app.Activity

import android.util.Log
import com.example.poke1.Domain.LoginInteractor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.util.Patterns


class LoginPresenter {

    companion object {
        private const val TAG = "LoginPresenter"
    }

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
        view?.showDelay(true)

        auth.signInWithEmailAndPassword(model.mail, model.pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    view?.userOk()
                } else {
                    Log.w(TAG, " fail to signIn", task.exception)
                    view?.showError(task.exception.toString())
                }
            }
        view?.showDelay(false)


    }
}



