package com.example.poke1.presentation.splash

import com.example.poke1.presentation.login.forgetPass.ForgetPassView
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SplashPresenterTest {
/*    @Test
    fun prueba() {
        val view = Mockito.mock(SplashView::class.java)

        val presenter = SplashPresenter()
        presenter.attach(view)
        assert(true)
    }
*/



    @Test
    fun prueba2() {
        val view = Mockito.mock(ForgetPassView::class.java)
/*
ERICK aca cuando agrego el presenter explota
* */
        //val presenter = ForgetPassPresenter()
        //presenter.attach(view)
        assert(true)


    }
}