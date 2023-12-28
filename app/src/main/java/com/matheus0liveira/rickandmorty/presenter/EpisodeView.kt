package com.matheus0liveira.rickandmorty.presenter

import com.matheus0liveira.rickandmorty.model.Episode

interface EpisodeView {

    fun showEpisodes(episodes: List<Episode>)
    fun showEpisodeProgress()
    fun hideEpisodeProgress()
    fun showError(message: String)
}