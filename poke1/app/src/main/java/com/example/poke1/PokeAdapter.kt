package com.example.poke1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.templatepoke.view.*

class PokeAdapter : RecyclerView.Adapter<PokeAdapter.PokeViewHolder>() {
    var listPokemos = ArrayList<Pokemon>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.templatepoke,parent,false)
        return PokeViewHolder(v)
    }

    override fun getItemCount(): Int {
        return listPokemos.size
    }

    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {
        holder.bind(listPokemos[position])
    }

    class PokeViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bind(poke: Pokemon) {
            view.txtNombre.text = poke.namePoke
            view.txtImagenUrl.text = poke.image
            view.imageView.setImageBitmap(poke.imagenBitmap)
        }
    }
}