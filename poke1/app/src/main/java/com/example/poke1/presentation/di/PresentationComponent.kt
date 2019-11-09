package com.example.poke1.presentation.di

import com.example.poke1.presentation.login.forgetPass.ForgetPassActivity
import com.example.poke1.presentation.login.forgetPass.ForgetPassPresenter
import com.example.poke1.presentation.login.login.LoginActivity
import com.example.poke1.presentation.login.newAccount.NewAccountActivity

import dagger.Component



@Component(modules=[PresentationModule::class])
interface PresentationComponent {
    fun injection(loginActivity: LoginActivity)
    fun injection(forgetPassActivity: ForgetPassActivity)
    fun injection(newAccountActivity: NewAccountActivity)
}