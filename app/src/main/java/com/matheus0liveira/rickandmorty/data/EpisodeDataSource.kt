package com.matheus0liveira.rickandmorty.data

import com.matheus0liveira.rickandmorty.model.Character
import com.matheus0liveira.rickandmorty.model.CharacterAPI
import com.matheus0liveira.rickandmorty.model.CharacterDetails
import com.matheus0liveira.rickandmorty.model.Episode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class EpisodeDataSource {


    fun findEpisodesBy(episodes: String, callback: EpisodeCallback) {
        HttpClient.retrofit()
            .create(RickAndMortyAPI::class.java)
            .findEpisodeBy(episodes)
            .enqueue(
                object : Callback<List<Episode>> {
                    override fun onResponse(
                        call: Call<List<Episode>>,
                        response: Response<List<Episode>>
                    ) {
                        val error = response.errorBody()?.toString()
                        if (error != null) {
                            callback.onError("Characters not found")
                            return
                        }

                        callback.onSuccess(
                            response.body() ?: emptyList<Episode>(),
                        )
                    }

                    override fun onFailure(call: Call<List<Episode>>, t: Throwable) {
                        callback.onError(t.message ?: "Unknown Error")
                    }

                }
            )
    }


}