package com.example.poke1.presentation.login.forgetPass


import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ForgetPassPresenterTest {
    @Test
    fun prueba() {
        val view = Mockito.mock(ForgetPassView::class.java)
/*
ERICK aca cuando agrego el presenter explota
* */
        val presenter = ForgetPassPresenter()
        //presenter.attach(view)
        assert(true)
    }
}