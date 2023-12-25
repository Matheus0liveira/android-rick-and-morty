package com.matheus0liveira.rickandmorty.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpClient {

    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    private fun httpClient() = OkHttpClient.Builder().build()

    fun retrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}