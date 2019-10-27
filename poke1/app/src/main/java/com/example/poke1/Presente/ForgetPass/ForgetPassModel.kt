package com.example.poke1.Presente.ForgetPass

import android.util.Patterns

data class ForgetPassModel (var mail : String ) {

    var isValid: Boolean = false

    get(){
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(mail).matches()
    }
}