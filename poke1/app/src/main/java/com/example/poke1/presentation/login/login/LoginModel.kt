package com.example.poke1.presentation.login.login

import android.util.Patterns


data class LoginModel(var mail: String, var pass: String) {
    var isValid: Boolean = false
        get() {
            var pattern = Patterns.EMAIL_ADDRESS
            var a = mail.isEmpty()
            var b = pass.isEmpty()
            var c = pattern.matcher(mail).matches()
            return (!mail.isEmpty() &&
                    !pass.isEmpty() &&
                    pattern.matcher(mail).matches())
        }
}