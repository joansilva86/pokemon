package com.example.poke1.presentation.login.newAccount

import android.util.Patterns

data class NewAccountModel(
    var name: String,
    var lastName: String,
    var mail: String,
    var pass: String,
    var pass2: String
) {
    var isValid: Boolean = false
        get() {
            val pattern = Patterns.EMAIL_ADDRESS

            return (!mail.isEmpty() &&
                    !name.isEmpty() &&
                    !lastName.isEmpty() &&
                    !pass.isEmpty() &&
                    !pass2.isEmpty() &&
                    (pass == pass2) &&
                    pattern.matcher(mail).matches()
                    )


        }
}