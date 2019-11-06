package com.example.poke1

import androidx.test.platform.app.InstrumentationRegistry
//import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.runner.AndroidJUnit4
import com.example.poke1.presentation.login.login.LoginModel
import com.example.poke1.presentation.login.login.LoginPresenter
import com.example.poke1.presentation.login.login.LoginView

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class ExampleInstrumentedTest {
    /*@Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.poke1", appContext.packageName)
    }*/

    @Test
    fun loginSucceed() {
        val view = Mockito.mock(LoginView::class.java)
        var model = LoginModel("joan.silva.1986@gmail.com","asd123")
        var presenter = LoginPresenter()
        presenter.attach(view)
        presenter.login(model)
        Mockito.verify(view).userOk()
    }
    @Test
    fun loginInvalidUser(){
        val view = Mockito.mock(LoginView::class.java)
        var model = LoginModel("invalidMail@gmail.com","asd123")
        var presenter = LoginPresenter()
        presenter.attach(view)
        presenter.login(model)
        Mockito.verify(view).showError("")
    }

    @Test
    fun loginIncorrectMail(){
        val view = Mockito.mock(LoginView::class.java)
        var model = LoginModel("No_Mail","asd123")
        var presenter = LoginPresenter()
        presenter.attach(view)
        presenter.login(model)
        Mockito.verify(view).invalidFormatEmail()
    }

    @Test
    fun loginEmptyPass(){
        val view = Mockito.mock(LoginView::class.java)
        var model = LoginModel("No_Mail","")
        var presenter = LoginPresenter()
        presenter.attach(view)
        presenter.login(model)
        Mockito.verify(view).passEmpty()
    }

    @Test
    fun loginEmptyUser(){
        val view = Mockito.mock(LoginView::class.java)
        var model = LoginModel("No_Mail","asd123")
        var presenter = LoginPresenter()
        presenter.attach(view)
        presenter.login(model)
        Mockito.verify(view).passEmpty()
    }

}
