package com.example.poke1.domain.loginInteractor


import com.example.poke1.presentation.login.forgetPass.ForgetPassModel
import com.example.poke1.presentation.login.login.LoginModel
import com.example.poke1.presentation.login.newAccount.NewAccountModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class LoginInteractor : LoginInteractorI {


    var auth = FirebaseAuth.getInstance()
    var db = FirebaseDatabase.getInstance()
    var dbReference: DatabaseReference

    init {

        dbReference = db.reference.child("User")
    }

    override fun signInGoogle(
        acct: GoogleSignInAccount,
        callback: LoginInteractorI.SignInGoogleCallBack
    ) {

        //val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        //val account = task.getResult(ApiException::class.java)


        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    callback.signInGoogleOk()
                    val user = auth.currentUser
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    callback.signInGoogleFail()
                    //Snackbar.make(main_layout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                    //updateUI(null)
                }

                // [START_EXCLUDE]
                //hideProgressDialog()
                // [END_EXCLUDE]
            }
    }

    override fun sendPasswordResetEmail(
        forgetPassModel: ForgetPassModel,
        callback: LoginInteractorI.SendPasswordResetEmailCallBack
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

    override suspend fun signIn(model: LoginModel): Unit =
        suspendCancellableCoroutine { continuation ->
            auth.signInWithEmailAndPassword(model.mail, model.pass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resume(Unit)
                    } else {
                        continuation.resumeWithException(FirebaseLoginExcepion(task.exception?.message))
                    }
                }
        }

    override fun createUser(model: NewAccountModel, callback: LoginInteractorI.CreateUserCallBack) {
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


}