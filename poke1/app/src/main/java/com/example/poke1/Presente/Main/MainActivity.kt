package com.example.poke1.Presente.Main

import android.content.Intent

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.poke1.*
import com.example.poke1.Presente.Base.BaseActivity
import com.example.poke1.Detail.DetailActivity
import com.example.poke1.Service.VolleyS
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainView {
    var presenter = MainPresenter(this)

    override fun showError() {
        Toast(R.string.errorServicio.toString())
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

    override fun getLayout(): Int {
        return R.layout.activity_main
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recycler.layoutManager = LinearLayoutManager(this)
        var vo = VolleyS.getInstance(this)
        recycler.adapter =
            PokeAdapter(vo.imageLoader!!, listenerRecyclerClick)

    }
}
