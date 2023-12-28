package com.matheus0liveira.rickandmorty.view

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.matheus0liveira.rickandmorty.R
import com.matheus0liveira.rickandmorty.model.Character
import com.squareup.picasso.Picasso


class HomeAdapter(
    private val items: List<Character>,
    val onClickListener: ((item: Character) -> Unit)? = null
) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.character_item, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(character: Character) {


            onClickListener?.let {
                itemView.setOnClickListener { onClickListener.invoke(character) }
            }


            val txtName = itemView.findViewById<TextView>(R.id.character_name)
            val txtOrigin = itemView.findViewById<TextView>(R.id.character_origin)
            val txtSpecie = itemView.findViewById<TextView>(R.id.character_specie)
            val txtStatus = itemView.findViewById<TextView>(R.id.character_status)
            val imgView = itemView.findViewById<ImageView>(R.id.character_img)



            imgView.clipToOutline = true
            Picasso.get().load(character.imgUrl).into(imgView);
            txtName.text = character.name
            txtSpecie.text = itemView.context.getString(R.string.specie, character.specie)
            txtOrigin.text = itemView.context.getString(R.string.location, character.origin)
            txtStatus.text = itemView.context.getString(R.string.status, character.status)
        }
    }
}