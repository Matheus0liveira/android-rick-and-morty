package com.matheus0liveira.rickandmorty.data

import android.util.Log
import com.matheus0liveira.rickandmorty.model.Character
import com.matheus0liveira.rickandmorty.model.CharacterAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class CharacterDataSource {

    fun findAllCharacters(callback: CharacterCallback) {
        HttpClient.retrofit()
            .create(RickAndMortyAPI::class.java)
            .findAllCharacters()
            .enqueue(
                object : Callback<CharacterAPI> {
                    override fun onResponse(
                        call: Call<CharacterAPI>,
                        response: Response<CharacterAPI>
                    ) {
                        callback.onSuccess(
                            response.body() ?: throw RuntimeException("Characters not found")
                        )
                    }

                    override fun onFailure(call: Call<CharacterAPI>, t: Throwable) {
                        callback.onError(t.message ?: "Unknown Error")
                    }

                }
            )
    }
}