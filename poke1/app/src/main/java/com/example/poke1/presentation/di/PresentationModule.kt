package com.example.poke1.presentation.di

import com.example.poke1.domain.loginInteractor.LoginInteractor
import com.example.poke1.domain.loginInteractor.LoginInteractorI
import com.example.poke1.presentation.login.forgetPass.ForgetPassPresenter
import com.example.poke1.presentation.login.login.LoginPresenter
import com.example.poke1.presentation.login.newAccount.NewAccountPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {


    @Provides
    fun providesLoginInteractor(): LoginInteractorI = LoginInteractor()

    /*@Provides
    fun providesForgetPassPresenter(loginInteractor: LoginInteractorI):ForgetPassPresenter=
        ForgetPassPresenter(loginInteractor)

    @Provides
    fun providesNewAccountPresenter(loginInteractor: LoginInteractorI):NewAccountPresenter =
        NewAccountPresenter(loginInteractor)*/
}