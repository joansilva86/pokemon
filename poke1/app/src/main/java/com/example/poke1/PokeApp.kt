package com.example.poke1

import android.app.Application
import com.example.poke1.presentation.di.PresentationComponent
import com.example.poke1.presentation.di.DaggerPresentationComponent
import com.example.poke1.presentation.di.PresentationModule


class PokeApp : Application () {
    private lateinit var appComponent: PresentationComponent

    fun getAppComponent() = appComponent

    override fun onCreate() {
        super.onCreate()
         appComponent = DaggerPresentationComponent
             .builder()
             .presentationModule(PresentationModule())
             .build()

    }

}