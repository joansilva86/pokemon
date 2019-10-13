package com.example.poke1.Presente.Login

import android.content.Intent

import android.os.Bundle
import android.view.View
import com.example.poke1.Presente.Base.BaseActivity
import com.example.poke1.Presente.Main.MainActivity
import com.example.poke1.Presente.NewAccount.NewAccountActivity

import com.example.poke1.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity(), LoginView {

    var presenter = LoginPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btnSignIn.setOnClickListener{
            presenter.login(txtMail.text.toString(),txtPass.text.toString())
        }
        btnSignGoogle.setOnClickListener {
            //todo
        }
        btnNewAccount.setOnClickListener {
            val intent = Intent(this, NewAccountActivity::class.java)
            startActivity(intent) }

    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }

    override fun getLayout(): Int {
        return R.layout.activity_login
    }


    override fun showDelay() {
        progressBar.visibility = View.VISIBLE
        btnSignIn.visibility = View.INVISIBLE
    }

    override fun showCreateAcount() {
        val intent = Intent(this, NewAccountActivity::class.java)
        startActivity(intent)
    }

    override fun showError() {
        Toast("ocurrio un error al conectar al servidor")
    }

    override fun userOk() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun userEmpty() {
        txtLayoutMail.error = getString(R.string.field_empty)
    }

    override fun passEmpty() {
        txtLayoutPass.error = getString(R.string.field_empty)
    }


}
