package com.example.poke1.Presente.NewAccount

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.poke1.Presente.Base.BaseActivity
import com.example.poke1.R
import kotlinx.android.synthetic.main.activity_new_account.*

class NewAccountActivity : BaseActivity(),NewAccountView {

    val presenter = NewAccountPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btnNewAccount.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }

    override fun showErrorCreate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorPassword() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorEmail() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNewUserOk() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayout(): Int {
        return R.layout.activity_new_account
    }


}
