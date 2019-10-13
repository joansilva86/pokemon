package com.example.poke1.Presente.Base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
    }

    abstract fun getLayout(): Int

    fun Toast(msg :String ){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}


