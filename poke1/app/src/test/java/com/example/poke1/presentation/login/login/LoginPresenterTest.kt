package com.example.poke1.presentation.login.login

import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.runners.MockitoJUnitRunner
/*
//@RunWith(MockitoJUnitRunner.class)
class LoginPresenterTest {

  /*  @Mock
    private val view : LoginView*/

    @Test
    fun login() {
        val view = Mockito.mock(LoginView::class.java)

        var model = LoginModel("joan.silva.1986@gmail.com","asd123")
        var presenter = LoginPresenter()
        presenter.attach(view)
        presenter.login(model)
        verify(view).userOk()
    }*/
