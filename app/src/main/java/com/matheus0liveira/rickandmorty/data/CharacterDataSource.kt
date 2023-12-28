package com.matheus0liveira.rickandmorty.data

import com.matheus0liveira.rickandmorty.model.CharacterAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class CharacterDataSource {

    fun findAllCharacters(currentPage: Int = 1, searchName: String?, callback: CharacterCallback) {
        HttpClient.retrofit()
            .create(RickAndMortyAPI::class.java)
            .findAllCharacters(currentPage, searchName)
            .enqueue(
                object : Callback<CharacterAPI> {
                    override fun onResponse(
                        call: Call<CharacterAPI>,
                        response: Response<CharacterAPI>
                    ) {
                        val error = response.errorBody()?.toString()
                        if (error != null) {
                            callback.onError("Characters not found")
                            return
                        }

                        callback.onSuccess(
                            response.body() ?: throw RuntimeException("Characters not found"),
                            response.body()?.info
                                ?: throw RuntimeException("Characters not found")
                        )
                    }

                    override fun onFailure(call: Call<CharacterAPI>, t: Throwable) {
                        callback.onError(t.message ?: "Unknown Error")
                    }

                }
            )
    }
}