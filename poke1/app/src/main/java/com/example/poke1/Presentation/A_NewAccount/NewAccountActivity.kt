package com.example.poke1.Presentation.A_NewAccount


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.poke1.Domain.LoginInteractor
import com.example.poke1.Presentation.Base.BaseActivity
import com.example.poke1.R


import kotlinx.android.synthetic.main.activity_new_account.*


import kotlinx.android.synthetic.main.activity_new_account.txtLayoutMail
import kotlinx.android.synthetic.main.activity_new_account.txtLayoutPass
import kotlinx.android.synthetic.main.activity_new_account.txtMail
import kotlinx.android.synthetic.main.activity_new_account.txtPass

class NewAccountActivity : BaseActivity(), NewAccountView {

    private val presenter = NewAccountPresenter(LoginInteractor())


    override fun getLayout(): Int {
        return R.layout.activity_new_account
    }


    /*****************************************/
    override fun onResume() {
        super.onResume()
        presenter.attach(this, this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        txtName.addTextChangedListener(onCleanError)
        txtLastName.addTextChangedListener(onCleanError)
        txtMail.addTextChangedListener(onCleanError)
        txtPass.addTextChangedListener(onCleanError)
        txtPass2.addTextChangedListener(onCleanError)

    }


    /************************************/


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
                txtLayoutName.error = null
                txtLayoutLastName.error = null
                txtLayoutPass.error = null
                txtLayoutPass2.error = null
                txtLayoutMail.error = null
            }
        }
    }


    fun newAccountListener(view: View) {

        var model = NewAccountModel(
            name = txtName.text.toString(),
            lastName = txtLastName.text.toString(),
            mail = txtMail.text.toString(),
            pass = txtPass.text.toString(),
            pass2 = txtPass2.text.toString()

        )

        presenter.createUser(model)

    }
    /*private var listener = object : NewAccountPresenter.ResponseListener{
        override fun responseOk() {
            Toast("Usuario Registrado Exitosamente")
            finish()
        }
        override fun responseFail() {
            Toast("No se pudo registrar el usuario")
        }

    }*/


    /*****************************************************************/

    override fun showUserOk() {
        Toast("Nuevo Usuario Registrado")
        finish()
    }

    override fun showUserFail(msj: String) {
        Toast("Ocurrio un error al crear el usuario " + msj)
    }

    override fun showDiferentPass() {
        Toast("las Contraseñas deben ser iguales")
    }

    override fun mailEmpty() {
        txtLayoutMail.error = "El campo no puede estar vacio"
    }

    override fun passEmpty() {
        txtLayoutPass.error = "El campo no puede estar vacio"
    }

    override fun pass2Empty() {
        txtLayoutPass2.error = "El campo no puede estar vacio"
    }

    override fun nameEmpty() {
        txtLayoutName.error = "El campo no puede estar vacio"
    }

    override fun lastNameEmpty() {
        txtLayoutLastName.error = "El campo no puede estar vacio"
    }

    override fun showDelay(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }

    }

    override fun invalidFormatEmail() {
        txtLayoutMail.error = getString(R.string.invalidFormatEmail)
    }

    override fun sendVerificationEmail(state: Boolean) {
        if (state) {
            Toast(getString(R.string.failSendVerificationEmail))
        } else {
            Toast(getString(R.string.okSendVerificationEmail))
        }
    }

}
