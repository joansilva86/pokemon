package com.example.poke1.presentation.login.login

import android.content.Intent


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.poke1.presentation.base.BaseActivity
import com.example.poke1.presentation.login.forgetPass.ForgetPassActivity
import com.example.poke1.presentation.Main.MainActivity
import com.example.poke1.presentation.login.newAccount.NewAccountActivity

import com.example.poke1.R
import com.facebook.CallbackManager
import kotlinx.android.synthetic.main.activity_login.*


import com.google.android.gms.auth.api.signin.GoogleSignIn

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback


class LoginActivity : BaseActivity(), LoginView{

    var presenter = LoginPresenter()

    private val onCleanError = object : TextWatcher {

        override fun afterTextChanged(s: Editable) {}

        override fun beforeTextChanged(
            s: CharSequence, start: Int,
            count: Int, after: Int
        ) {
        }

        override fun onTextChanged(
            s: CharSequence, start: Int,
            before: Int, count: Int
        ) {

            if (s.isEmpty()) {
            } else {
                txtLayoutPass.error = null
                txtLayoutMail.error = null
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_login
    }


    private lateinit var googleSignInClient: GoogleSignInClient

    private lateinit var callbackManager: CallbackManager

    companion object {
        private const val RC_SIGN_IN = 9001
        private const val SIGN_IN_FACEBOOK = 64206
    }


    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                presenter.loginWithGoogle(account!!)

            } catch (e: ApiException) {
                signInGoogleFail()
            }
        }
        if (requestCode == SIGN_IN_FACEBOOK) {
            callbackManager.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun signInGoogleOk() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun signInGoogleFail() {
        Toast(getString(R.string.sign_in_google_fail))
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        txtMail.addTextChangedListener(onCleanError)
        txtPass.addTextChangedListener(onCleanError)


        callbackManager = CallbackManager.Factory.create()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        btnSignGoogle.setOnClickListener {
            showDelay()
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent,
                RC_SIGN_IN
            )
        }


        btnSignIn.setOnClickListener {
            val model = LoginModel(
                txtMail.text.toString(),
                txtPass.text.toString()
            )
            presenter.login(model)
        }

        btnNewAccount.setOnClickListener {
            val intent = Intent(this, NewAccountActivity::class.java)
            startActivity(intent)
        }
        btnForgetPass.setOnClickListener {
            val intent = Intent(this, ForgetPassActivity::class.java)
            startActivity(intent)
        }


        btnSignInFace.setReadPermissions("email")

        // Callback registration
        btnSignInFace.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                userOk()
            }

            override fun onCancel() {
                Toast("se cancelo la operacion de registro con facebook")
            }

            override fun onError(exception: FacebookException) {
                showError(exception.toString())
            }
        })
    }


    override fun onResume() {
        super.onResume()
        presenter.attach(this, this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
        presenter.detachJob()
    }

    override fun hideDelay() {
        logoApp.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        btnSignIn.visibility = View.VISIBLE
        btnNewAccount.visibility = View.VISIBLE
        btnForgetPass.visibility = View.VISIBLE

    }

    override fun showDelay() {
        logoApp.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        btnSignIn.visibility = View.INVISIBLE
        btnNewAccount.visibility = View.INVISIBLE
        btnForgetPass.visibility = View.INVISIBLE
    }

    override fun showCreateAcount() {
        val intent = Intent(this, NewAccountActivity::class.java)
        startActivity(intent)
    }

    override fun showError(msj: String) {
        Toast("ocurrio un error al conectar al servidor: " + msj)

    }

    override fun userOk() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        //finish()
    }

    override fun userEmpty() {
        txtLayoutMail.error = getString(R.string.field_empty)
    }

    override fun passEmpty() {
        txtLayoutPass.error = getString(R.string.field_empty)
    }

    override fun invalidFormatEmail() {
        txtLayoutMail.error = getString(R.string.invalidFormatEmail)
    }
}
