package com.matheus0liveira.rickandmorty.data

import com.matheus0liveira.rickandmorty.model.CharacterAPI

interface CharacterCallback {

    fun onSuccess(characters: CharacterAPI)
    fun onError(message: String)
}