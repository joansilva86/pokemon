package com.example.poke1.Presentation.splash

import com.example.poke1.Presentation.Base.BasePresenter
import android.os.Handler

class SplashPresenter: BasePresenter {
    private val handler = Handler()
    var runnable = Runnable {
        this.view?.success()
    }
    var view : SplashView? = null

    fun attach(view: SplashView){
        this.view = view
    }
    override fun detach() {
        this.view = null

    }
    fun inicializeFramework(){
        handler.postDelayed(runnable,4000)

    }
    fun isLoged(): Boolean{
        return false
    }
}