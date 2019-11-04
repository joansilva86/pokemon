package com.example.poke1.domain.loginInteractor


import android.util.Log
import com.example.poke1.presentation.login.forgetPass.ForgetPassModel
import com.example.poke1.presentation.login.login.LoginModel
import com.example.poke1.presentation.login.newAccount.NewAccountModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.suspendCancellableCoroutine
import java.lang.Exception
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class LoginInteractor : LoginInteractorI {

    companion object {
        private const val TAG = "LoginInteractor"
    }

    var auth = FirebaseAuth.getInstance()
    var db = FirebaseDatabase.getInstance()
    var dbReference: DatabaseReference

    init {

        dbReference = db.reference.child("User")
    }

    override suspend fun signInGoogle(
        acct: GoogleSignInAccount): Unit = suspendCancellableCoroutine {continues->

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    continues.resume(Unit)
                    val user = auth.currentUser
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    continues.resumeWithException(GoogleSignInException(task.exception?.message.toString()))
                    //Snackbar.make(main_layout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                    //updateUI(null)
                }

            }
    }

    override suspend fun sendPasswordResetEmail(forgetPassModel: ForgetPassModel): Unit =
        suspendCancellableCoroutine { continuation ->
            auth.sendPasswordResetEmail(forgetPassModel.mail)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resume(Unit)
                    } else {
                        continuation.resumeWithException(FirebaseForgetPassException(task.exception?.message.toString()))
                    }
                }
        }

    override suspend fun signIn(model: LoginModel): Unit =
        suspendCancellableCoroutine { continuation ->
            FirebaseAuth.getInstance().signInWithEmailAndPassword(model.mail, model.pass)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        continuation.resume(Unit)
                    } else {
                        continuation.resumeWithException(FirebaseLoginException(it.exception?.message))
                    }
                }
        }

    override suspend fun createUser(model: NewAccountModel): Unit =
        suspendCancellableCoroutine { continuation ->
            auth.createUserWithEmailAndPassword(model.mail, model.pass)
                .addOnCompleteListener { task ->
                    if (task.isComplete && task.isSuccessful) {
                        sendEmailVerification()
                        val user = auth.currentUser
                        val userDB = dbReference.child(user?.uid)
                        userDB.child("Name").setValue(model.name)
                        userDB.child("LasName").setValue(model.lastName)
                        continuation.resume(Unit)
                    } else {
                        continuation.resumeWithException(FirebaseCreateUserException(task.exception?.message))
                    }
                }
        }


    private fun sendEmailVerification() {
        val user = auth.currentUser
        user?.sendEmailVerification()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    /******************/
                    Log.d(TAG, "sendEmailVerification", task.exception)
                    /******************/
                } else {
                    Log.e(TAG, "sendEmailVerification", task.exception)

                }

            }

    }


}