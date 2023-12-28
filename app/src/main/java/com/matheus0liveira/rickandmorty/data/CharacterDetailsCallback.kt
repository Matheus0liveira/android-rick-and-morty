package com.matheus0liveira.rickandmorty.data

import com.matheus0liveira.rickandmorty.model.Character
import com.matheus0liveira.rickandmorty.model.CharacterAPI
import com.matheus0liveira.rickandmorty.model.CharacterDetails
import com.matheus0liveira.rickandmorty.model.Info

interface CharacterDetailsCallback {

    fun onSuccess(characterDetails: CharacterDetails)
    fun onError(message: String)
}