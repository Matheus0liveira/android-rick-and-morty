package com.matheus0liveira.rickandmorty.view

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matheus0liveira.rickandmorty.R
import com.matheus0liveira.rickandmorty.model.Character
import com.squareup.picasso.Picasso

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val characters = mutableListOf<Character>()


        characters.add(
            Character(
                id = 1,
                name = "Rick Sanchez",
                status = "Alive",
                origin = "Earth",
                specie = "Human",
                imgUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"

            )
        )
        characters.add(
            Character(
                id = 1,
                name = "Morty Smith",
                status = "Alive",
                origin = "Earth",
                specie = "Human",
                imgUrl = "https://rickandmortyapi.com/api/character/avatar/2.jpeg"

            )
        )

        val adapter = MainAdapter(characters)
        val rv = findViewById<RecyclerView>(R.id.rv_main)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

    }


    inner class MainAdapter(private val items: List<Character>) :
        RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view = layoutInflater.inflate(R.layout.character_item, parent, false)
            return MainViewHolder(view)
        }

        override fun getItemCount() = items.size
        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.bind(items[position])
        }

        inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(character: Character) {

                val txtName = itemView.findViewById<TextView>(R.id.character_name)
                val txtOrigin = itemView.findViewById<TextView>(R.id.character_origin)
                val txtSpecie = itemView.findViewById<TextView>(R.id.character_specie)
                val txtStatus = itemView.findViewById<TextView>(R.id.character_status)
                val imgView = itemView.findViewById<ImageView>(R.id.character_img)

                Picasso.get().load(character.imgUrl).into(imgView);
                txtName.text = character.name.uppercase()
                txtSpecie.text = getString(R.string.specie, character.specie)
                txtOrigin.text = getString(R.string.location, character.origin)
                txtStatus.text = getString(R.string.status, character.status)

            }

        }

    }
}

