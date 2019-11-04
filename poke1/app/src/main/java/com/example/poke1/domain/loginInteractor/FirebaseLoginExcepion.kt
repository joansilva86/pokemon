package com.example.poke1.domain.loginInteractor

class FirebaseLoginException(message:String?): Exception(message)
class FirebaseCreateUserException(message:String?): Exception(message)
class FirebaseForgetPassException(message: String?): Exception(message)
class GoogleSignInException (message: String?): Exception(message)