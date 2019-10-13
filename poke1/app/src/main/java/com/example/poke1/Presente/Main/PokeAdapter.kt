package com.example.poke1.Presente.Main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.ImageLoader
import com.example.poke1.R
import kotlinx.android.synthetic.main.templatepoke.view.*

class PokeAdapter(var imageLoader: ImageLoader, var listener: ListenerRecyclerClick) :
    RecyclerView.Adapter<PokeAdapter.PokeViewHolder>() {
    var listPokemos = ArrayList<Pokemon>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.templatepoke, parent, false)
        return PokeViewHolder(v, listener)
    }

    override fun getItemCount(): Int {
        return listPokemos.size
    }

    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {
        holder.bind(listPokemos[position], this.imageLoader)
    }

    class PokeViewHolder(var view: View, var listener: ListenerRecyclerClick) :
        RecyclerView.ViewHolder(view) {

        var listenerJoan = object : View.OnClickListener {
            override fun onClick(v: View?) {
                listener.onClick(adapterPosition)
            }
        }

        init {
            this.view.setOnClickListener { listener.onClick(adapterPosition) }
            //this.view.setOnClickListener(listenerJoan)
        }

        fun bind(poke: Pokemon, imageLoader: ImageLoader) {
            view.txtNombre.text = poke.namePoke

            //view.imageView.setImageBitmap(poke.imagenBitmap)
            view.imgPic.setImageUrl(poke.imageUrl, imageLoader)
        }
    }
}

interface ListenerRecyclerClick {
    fun onClick(id: Int)

}