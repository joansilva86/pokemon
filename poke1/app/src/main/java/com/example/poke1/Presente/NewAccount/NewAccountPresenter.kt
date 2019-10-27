package com.example.poke1.Presente.NewAccount

import android.app.Activity
import android.util.Log
import android.util.Patterns
import com.example.poke1.Presente.Base.BasePresenter
import com.example.poke1.Presente.Login.LoginPresenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class NewAccountPresenter : BasePresenter {

    var view: NewAccountView? = null
    var activity: Activity? = null
    var db = FirebaseDatabase.getInstance()
    var auth = FirebaseAuth.getInstance()
    var dbReference: DatabaseReference

    companion object {
        private const val TAG = "NewAccount"
    }

    init {

        dbReference = db.reference.child("User")
    }

    override fun detach() {
        view = null
    }

    fun attach(view: NewAccountView, activity: Activity) {
        this.view = view
        this.activity = activity
    }

    private fun validarEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    fun createUser(model: NewAccountModel) {
        if (model.mail.isEmpty()) {
            view?.mailEmpty()
        }
        if (model.name.isEmpty()) {
            view?.nameEmpty()
        }
        if (model.lastName.isEmpty()) {
            view?.lastNameEmpty()
        }

        if (model.pass.isEmpty()) {
            view?.passEmpty()
        }
        if (model.pass2.isEmpty()) {
            view?.pass2Empty()
        }

        if (!validarEmail(model.mail)) {
            view?.invalidFormatEmail()
        }

        if (model.pass != model.pass2) {
            view?.showDiferentPass()
        }
        if (!model.isValid) {
            return
        }
        /*********************/
        //Erick te podras fijar esto
        /*********************/
        view?.showDelay(true)
        auth.createUserWithEmailAndPassword(model.mail, model.pass)
            .addOnCompleteListener(activity!!) { task ->
                if (task.isComplete && task.isSuccessful) {
                    sendEmailVerification()
                    val user = auth.currentUser
                    val userDB = dbReference.child(user?.uid)
                    userDB.child("Name").setValue(model.name)
                    userDB.child("LasName").setValue(model.lastName)

                    view?.showUserOk()
                } else {
                    view?.showUserFail(task.exception.toString())
                }

            }
        view?.showDelay(false)

    }

    private fun sendEmailVerification() {
        val user = auth.currentUser
        user?.sendEmailVerification()
            ?.addOnCompleteListener(activity!!) { task ->
                if (task.isSuccessful) {
                    view?.sendVerificationEmail(true)
                } else {
                    Log.e(TAG, "sendEmailVerification", task.exception)
                    view?.sendVerificationEmail(false)
                }

            }

    }

}

