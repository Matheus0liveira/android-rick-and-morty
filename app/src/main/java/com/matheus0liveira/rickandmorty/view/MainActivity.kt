package com.matheus0liveira.rickandmorty.view

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matheus0liveira.rickandmorty.R
import com.matheus0liveira.rickandmorty.model.Character
import com.matheus0liveira.rickandmorty.model.Info
import com.matheus0liveira.rickandmorty.presenter.CharacterPresenter
import com.matheus0liveira.rickandmorty.presenter.CharacterView
import com.squareup.picasso.Picasso

class MainActivity : ComponentActivity(), CharacterView {

    private lateinit var presenter: CharacterPresenter
    private lateinit var adapter: MainAdapter
    private lateinit var characters: MutableList<Character>
    private lateinit var progressBar: FrameLayout
    private var currentPage = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        presenter = CharacterPresenter(this)
        progressBar = findViewById(R.id.progress_overlay)

        characters = mutableListOf()
        presenter.findAllCharacters(currentPage)

        adapter = MainAdapter(characters)
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
                imgView.clipToOutline = true
                Picasso.get().load(character.imgUrl).into(imgView);
                txtName.text = character.name
                txtSpecie.text = getString(R.string.specie, character.specie)
                txtOrigin.text = getString(R.string.location, character.origin)
                txtStatus.text = getString(R.string.status, character.status)

            }

        }

    }

    override fun showCharacter(character: List<Character>) {
        characters.clear()
        characters.addAll(character)
        adapter.notifyDataSetChanged()
    }

    override fun handleInfo(info: Info) {

        val prevBtn = findViewById<Button>(R.id.prev_btn)
        val nextBtn = findViewById<Button>(R.id.next_btn)
        val txtCurrentPage = findViewById<TextView>(R.id.txt_current_page)
        txtCurrentPage.text = currentPage.toString()
        prevBtn.setOnClickListener {
            --currentPage
            presenter.findAllCharacters(currentPage)
        }
        nextBtn.setOnClickListener {
            ++currentPage
            presenter.findAllCharacters(currentPage)
        }

        prevBtn.setTextColor(resources.getColor(R.color.gray, theme))

        if (info.prev == null) {
            prevBtn.isEnabled = false
            prevBtn.setTextColor(resources.getColor(R.color.gray, theme))
        } else {
            prevBtn.isEnabled = true
            prevBtn.setTextColor(resources.getColor(R.color.dark_blue, theme))
        }
        if (info.next == null) {
            nextBtn.isEnabled = false
            nextBtn.setTextColor(resources.getColor(R.color.gray, theme))
        } else {
            nextBtn.isEnabled = true
            nextBtn.setTextColor(resources.getColor(R.color.dark_blue, theme))
        }


    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showError(message: String) {
        TODO("Not yet implemented")
    }
}

