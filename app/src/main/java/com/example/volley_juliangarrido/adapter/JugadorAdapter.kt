package com.example.volley_juliangarrido.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.volley_juliangarrido.R
import com.example.volley_juliangarrido.model.entities.Personaje

class JugadorAdapter(
    private val itemList: List<Personaje>,
    private val onItemClick: (Personaje) -> Unit
) : RecyclerView.Adapter<JugadorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_jugador, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val textItemName: TextView = itemView.findViewById(R.id.nombre)
        private val fotoItemName: ImageView = itemView.findViewById(R.id.ivPersonaje)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(personaje: Personaje) {
            textItemName.text = personaje.name
            Glide.with(fotoItemName.context).load(personaje.imagen).into(fotoItemName)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onItemClick(itemList[position])
            }
        }
    }
}