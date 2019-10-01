package com.example.poke1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.poke1.Service.VolleyS
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

    var listenerRecyclerClick = object : ListenerRecyclerClick {
        override fun onClick(id: Int) {
            val intento1 = Intent(this@MainActivity, DetailActivity::class.java)
            startActivity(intento1)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler.layoutManager = LinearLayoutManager(this)
        var vo = VolleyS.getInstance(this)
        recycler.adapter = PokeAdapter(vo.imageLoader!!,listenerRecyclerClick )

    }
}
