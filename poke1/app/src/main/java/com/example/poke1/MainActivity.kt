package com.example.poke1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {
    var presenter = MainPresenter(this)

    override fun showError() {

    }

    override fun showList(list: ArrayList<Pokemon>) {
        (recycler.adapter as PokeAdapter).listPokemos = list
    }


    override fun onResume() {
        super.onResume()
        presenter.attach(this)
        presenter.begin(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = PokeAdapter()

    }
}
