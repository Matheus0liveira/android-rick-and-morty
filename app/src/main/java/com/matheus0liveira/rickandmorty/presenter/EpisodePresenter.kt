package com.matheus0liveira.rickandmorty.presenter

import com.matheus0liveira.rickandmorty.data.EpisodeCallback
import com.matheus0liveira.rickandmorty.data.EpisodeDataSource
import com.matheus0liveira.rickandmorty.model.Episode

class EpisodePresenter(
    private val callback: EpisodeView,
    private val dataSource: EpisodeDataSource = EpisodeDataSource(),
) {
    fun findEpisodesBy(episodes: String) {
        callback.showEpisodeProgress()
        dataSource.findEpisodesBy(episodes, object : EpisodeCallback {
            override fun onSuccess(episodes: List<Episode>) {

                callback.showEpisodes(episodes)
                callback.hideEpisodeProgress()
            }

            override fun onError(message: String) {
                callback.showError(message)
                callback.hideEpisodeProgress()
            }

        })
    }


}