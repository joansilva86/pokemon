package com.example.poke1.Presente.ForgetPass


import com.example.poke1.Presente.Base.BaseActivity
import com.example.poke1.R

class ForgetPassActivity : BaseActivity(), ForgetPassView {

    private val presenter = ForgetPassPresenter()

    override fun getLayout(): Int {
        return R.layout.activity_forget_pass
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }



    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun recoverPassOk() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }




}
