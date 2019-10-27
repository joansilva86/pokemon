package com.example.poke1.Presente.Login

import android.content.Intent

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.poke1.Presente.Base.BaseActivity
import com.example.poke1.Presente.ForgetPass.ForgetPassActivity
import com.example.poke1.Presente.Main.MainActivity
import com.example.poke1.Presente.NewAccount.NewAccountActivity

import com.example.poke1.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity(), LoginView {

    var presenter = LoginPresenter()

    /***************/
//BUScar una forma de escribir esto con expreciones lambda
    /***************/
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        txtMail.addTextChangedListener(onCleanError)
        txtPass.addTextChangedListener(onCleanError)

        btnSignIn.setOnClickListener {
            val model = LoginModel(txtMail.text.toString(), txtPass.text.toString())
            presenter.login(model)
        }
        btnSignGoogle.setOnClickListener {
            //todo
        }
        btnNewAccount.setOnClickListener {
            val intent = Intent(this, NewAccountActivity::class.java)
            startActivity(intent)
        }
        btnForgetPass.setOnClickListener{
            val intent = Intent(this, ForgetPassActivity::class.java)
            startActivity(intent)}

    }


    override fun onResume() {
        super.onResume()
        presenter.attach(this, this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }



    override fun showDelay(state: Boolean) {
        if(state) {
            progressBar.visibility = View.VISIBLE
            btnSignIn.visibility = View.INVISIBLE
            btnNewAccount.visibility = View.INVISIBLE
            btnForgetPass.visibility = View.INVISIBLE
        }
        else {
            progressBar.visibility = View.GONE

            btnSignIn.visibility = View.VISIBLE
            btnNewAccount.visibility = View.VISIBLE
            btnForgetPass.visibility = View.VISIBLE
        }
    }

    override fun showCreateAcount() {
        val intent = Intent(this, NewAccountActivity::class.java)
        startActivity(intent)
    }

    override fun showError(msj : String) {
        Toast("ocurrio un error al conectar al servidor: "+ msj)

    }

    override fun userOk() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
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
