package com.matheus0liveira.rickandmorty.model

import com.google.gson.annotations.SerializedName


data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val origin: String,
    val specie: String,
    val imgUrl: String
)


data class Info(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: Any? = null
)

data class Result(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Location,
    val location: Location,
    val image: String
)

data class Location(
    val name: String,
    val url: String
)

data class CharacterAPI(
    val info: Info,
    val results: List<Result>
)