package com.example.poke1.Presente.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.poke1.Presente.Base.BaseActivity
import com.example.poke1.Presente.Login.LoginActivity
import com.example.poke1.Presente.Main.MainActivity
import com.example.poke1.R
import kotlinx.android.synthetic.main.activity_new_account.*

class SplashActivity : BaseActivity (), SplashView{

    var presenter = SplashPresenter()

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
        presenter.inicializeFramework()
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressBar.visibility = View.VISIBLE
    }

    override fun success() {

        if(presenter.isLoged()) {
            startActivity(Intent(this, MainActivity::class.java))
        }
        else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        finish()
    }

    override fun showError() {
        Toast("Ocurrio un error al inicializar la aplicacion")
    }

    override fun getLayout(): Int {
        return R.layout.activity_splash
    }


}
