package com.matheus0liveira.rickandmorty.data

import com.matheus0liveira.rickandmorty.model.Character
import com.matheus0liveira.rickandmorty.model.CharacterAPI
import com.matheus0liveira.rickandmorty.model.CharacterDetails
import com.matheus0liveira.rickandmorty.model.Episode
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyAPI {

    @GET("character")
    fun findAllCharacters(
        @Query("page") currentPage: Int = 1,
        @Query("name") name: String? = ""
    ): Call<CharacterAPI>


    @GET("character/{id}")
    fun findCharacterBy(@Path("id") id: Int): Call<CharacterDetails>

    @GET("episode/{episodes}")
    fun findEpisodeBy(@Path("episodes") episodes: String): Call<List<Episode>>
}

