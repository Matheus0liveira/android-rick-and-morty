package com.matheus0liveira.rickandmorty.data

import com.matheus0liveira.rickandmorty.model.Character
import com.matheus0liveira.rickandmorty.model.CharacterAPI
import retrofit2.Call
import retrofit2.http.GET

interface RickAndMortyAPI {

    @GET("character")
    fun findAllCharacters(): Call<CharacterAPI>
}