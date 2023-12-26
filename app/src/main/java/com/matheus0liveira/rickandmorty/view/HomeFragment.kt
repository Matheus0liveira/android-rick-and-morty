package com.matheus0liveira.rickandmorty.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matheus0liveira.rickandmorty.R
import com.matheus0liveira.rickandmorty.presenter.CharacterPresenter
import com.matheus0liveira.rickandmorty.model.Character
import com.matheus0liveira.rickandmorty.model.Info
import com.matheus0liveira.rickandmorty.presenter.CharacterView
import com.squareup.picasso.Picasso

class HomeFragment : Fragment(), CharacterView {

    private lateinit var presenter: CharacterPresenter
    private lateinit var adapter: MainAdapter
    private lateinit var characters: MutableList<Character>
    private lateinit var progressBar: FrameLayout

    private lateinit var prevBtn: Button
    private lateinit var nextBtn: Button
    private lateinit var txtCurrentPage: TextView
    private var currentPage = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prevBtn = view.findViewById(R.id.prev_btn)
        nextBtn = view.findViewById(R.id.next_btn)
        progressBar = view.findViewById(R.id.progress_overlay)
        txtCurrentPage = view.findViewById(R.id.txt_current_page)


        presenter = CharacterPresenter(this)
        characters = mutableListOf()
        presenter.findAllCharacters(currentPage)

        adapter = MainAdapter(characters)
        val rv = view.findViewById<RecyclerView>(R.id.rv_main)
        rv.layoutManager = LinearLayoutManager(requireContext())
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

        txtCurrentPage.text = currentPage.toString()
        prevBtn.setOnClickListener {
            --currentPage
            presenter.findAllCharacters(currentPage)
        }
        nextBtn.setOnClickListener {
            ++currentPage
            presenter.findAllCharacters(currentPage)
        }

        prevBtn.setTextColor(resources.getColor(R.color.gray, resources.newTheme()))

        if (info.prev == null) {
            prevBtn.isEnabled = false
            prevBtn.setTextColor(resources.getColor(R.color.gray, resources.newTheme()))
        } else {
            prevBtn.isEnabled = true
            prevBtn.setTextColor(resources.getColor(R.color.dark_blue, resources.newTheme()))
        }
        if (info.next == null) {
            nextBtn.isEnabled = false
            nextBtn.setTextColor(resources.getColor(R.color.gray, resources.newTheme()))
        } else {
            nextBtn.isEnabled = true
            nextBtn.setTextColor(resources.getColor(R.color.dark_blue, resources.newTheme()))
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