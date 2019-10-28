package com.example.poke1.presentation.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.poke1.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailView {


    private var presenter = DetailPresenter(this)

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
        presenter.begin()
    }

    override fun onStop() {
        super.onStop()
        presenter.detach()
    }

    override fun showError() {
        txtNombre.setText("Error al traer el servicio")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    override fun showPokemonDetail(pokemon: PokemonDetail) {
        txtNombre.setText(pokemon.nombre)
        txtTipo.setText(pokemon.tipo)
        imagePokeBack.setImageBitmap(pokemon.imageBack)
        imagePokeFront.setImageBitmap(pokemon.imageFront)
    }
}
