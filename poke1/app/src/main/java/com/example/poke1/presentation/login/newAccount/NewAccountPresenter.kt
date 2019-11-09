package com.example.poke1.presentation.login.newAccount

import android.app.Activity
import android.util.Log
import android.util.Patterns
import com.example.poke1.domain.loginInteractor.FirebaseCreateUserException
import com.example.poke1.domain.loginInteractor.LoginInteractor
import com.example.poke1.domain.loginInteractor.LoginInteractorI
import com.example.poke1.presentation.base.BasePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class NewAccountPresenter @Inject constructor(private val interactor: LoginInteractorI ) : BasePresenter , CoroutineScope{

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    var view: NewAccountView? = null
    var activity: Activity? = null


    override fun detach() {
        view = null
    }

    fun attach(view: NewAccountView, activity: Activity) {
        this.view = view
        this.activity = activity
    }

    private fun validarEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    fun createUser(model: NewAccountModel) {
        if (model.mail.isEmpty()) {
            view?.mailEmpty()
        }
        if (model.name.isEmpty()) {
            view?.nameEmpty()
        }
        if (model.lastName.isEmpty()) {
            view?.lastNameEmpty()
        }

        if (model.pass.isEmpty()) {
            view?.passEmpty()
        }
        if (model.pass2.isEmpty()) {
            view?.pass2Empty()
        }

        if (!validarEmail(model.mail)) {
            view?.invalidFormatEmail()
        }

        if (model.pass != model.pass2) {
            view?.showDiferentPass()
        }
        if (!model.isValid) {
            return
        }

        /*********************/
        //Erick te podras fijar esto
        /*********************/
        view?.showDelay()

        launch {
            try {
                interactor.createUser(model)
                view?.showUserOk()
                view?.hideDelay()
            } catch (ex: FirebaseCreateUserException) {
                view?.showUserFail(ex.toString())
                view?.hideDelay()
            }
        }

    }


}

