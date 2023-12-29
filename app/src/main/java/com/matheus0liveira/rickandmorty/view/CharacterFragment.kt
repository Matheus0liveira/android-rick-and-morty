package com.matheus0liveira.rickandmorty.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matheus0liveira.rickandmorty.R
import com.matheus0liveira.rickandmorty.model.CharacterDetails
import com.matheus0liveira.rickandmorty.model.Episode
import com.matheus0liveira.rickandmorty.presenter.CharacterPresenter
import com.matheus0liveira.rickandmorty.presenter.CharacterView
import com.matheus0liveira.rickandmorty.presenter.EpisodePresenter
import com.matheus0liveira.rickandmorty.presenter.EpisodeView
import com.squareup.picasso.Picasso

class CharacterFragment : Fragment(R.layout.character_fragment), CharacterView, EpisodeView {

    companion object {
        const val ID_KEY = "id"
    }

    private lateinit var characterPresenter: CharacterPresenter
    private lateinit var episodePresenter: EpisodePresenter
    private lateinit var progressBar: FrameLayout
    private lateinit var episodeProgressBar: FrameLayout

    private lateinit var txtName: TextView
    private lateinit var txtOrigin: TextView
    private lateinit var txtSpecie: TextView
    private lateinit var txtStatus: TextView
    private lateinit var imgView: ImageView
    private lateinit var txtEpisodes: TextView
    private lateinit var adapter: EpisodeAdapter
    private lateinit var episodes: MutableList<Episode>
    override fun onCreate(savedInstanceState: Bundle?) {
        characterPresenter = CharacterPresenter(this)
        episodePresenter = EpisodePresenter(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characterId = arguments?.getInt(ID_KEY)

        txtName = view.findViewById(R.id.character_name)
        txtOrigin = view.findViewById(R.id.character_origin)
        txtSpecie = view.findViewById(R.id.character_specie)
        txtStatus = view.findViewById(R.id.character_status)
        imgView = view.findViewById(R.id.character_img)
        txtEpisodes = view.findViewById(R.id.txt_episodes)
        progressBar = view.findViewById(R.id.progress_overlay)
        episodeProgressBar = view.findViewById(R.id.episode_progress)

        val rv = view.findViewById<RecyclerView>(R.id.rv_episode)
        episodes = mutableListOf()
        adapter = EpisodeAdapter(episodes)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(requireContext())


        characterId?.let { characterPresenter.findCharacterBy(characterId) }

    }

    override fun showCharacter(characterDetails: CharacterDetails) {
        val toolbar = activity?.findViewById<Toolbar>(R.id.toolbar)
        toolbar?.title = characterDetails.name

        val episodes = characterDetails.episode.map {
            it.split("/").last()
        }

        episodePresenter.findEpisodesBy(episodes = episodes.joinToString(", "))


        imgView.clipToOutline = true
        Picasso.get().load(characterDetails.image).into(imgView);
        txtName.text = characterDetails.name
        txtSpecie.text = getString(R.string.specie, characterDetails.species)
        txtOrigin.text = getString(R.string.location, characterDetails.origin.name)
        txtStatus.text = getString(R.string.status, characterDetails.status)
        txtEpisodes.text = getString(R.string.all_episodes, characterDetails.name)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showEpisodes(episodes: List<Episode>) {
        this.episodes.clear()
        this.episodes.addAll(episodes)
        adapter.notifyDataSetChanged()
    }

    override fun showEpisodeProgress() {
        episodeProgressBar.visibility = View.VISIBLE
    }

    override fun hideEpisodeProgress() {
        episodeProgressBar.visibility = View.GONE
    }

    override fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }


}