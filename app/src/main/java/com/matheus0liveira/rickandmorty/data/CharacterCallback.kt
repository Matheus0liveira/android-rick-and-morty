package com.matheus0liveira.rickandmorty.data

import com.matheus0liveira.rickandmorty.model.CharacterAPI
import com.matheus0liveira.rickandmorty.model.Info

interface CharacterCallback {

    fun onSuccess(characters: CharacterAPI, info: Info)
    fun onError(message: String)
}