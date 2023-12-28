package com.matheus0liveira.rickandmorty.data

import com.matheus0liveira.rickandmorty.model.Character
import com.matheus0liveira.rickandmorty.model.CharacterAPI
import com.matheus0liveira.rickandmorty.model.CharacterDetails
import com.matheus0liveira.rickandmorty.model.Episode
import com.matheus0liveira.rickandmorty.model.Info

interface EpisodeCallback {

    fun onSuccess(episode: List<Episode>)
    fun onError(message: String)
}