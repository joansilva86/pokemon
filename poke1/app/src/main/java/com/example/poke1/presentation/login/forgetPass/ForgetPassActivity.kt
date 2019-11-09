package com.example.poke1.presentation.login.forgetPass


import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.poke1.PokeApp
import com.example.poke1.presentation.base.BaseActivity
import com.example.poke1.presentation.login.login.LoginActivity
import com.example.poke1.R
import com.example.poke1.domain.loginInteractor.LoginInteractor
import kotlinx.android.synthetic.main.activity_forget_pass.*
import javax.inject.Inject


class ForgetPassActivity : BaseActivity(),
    ForgetPassView {

    @Inject
    lateinit var presenter : ForgetPassPresenter

    override fun getLayout(): Int {
        return R.layout.activity_forget_pass
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as PokeApp).getAppComponent().injection(this)
        btnForget.setOnClickListener {
            val model =
                ForgetPassModel(txtMail.toString())
            presenter.forgetPass(model)
        }
    }

    override fun invalidFormatEmail() {
        txtLayoutMail.error = getString(R.string.invalidFormatEmail)
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }

    override fun showError(string: String) {
        Toast(string)
    }

    override fun recoverPassOk() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }


    override fun mailEmpty() {
        txtLayoutMail.error = getString(R.string.field_empty)
    }
    override fun showDelay(state:Boolean){
        if(state){
            progressBar.visibility = View.VISIBLE
        }
        else{
            progressBar.visibility = View.GONE
        }
    }
}
