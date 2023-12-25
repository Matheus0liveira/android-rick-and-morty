package com.matheus0liveira.rickandmorty.model

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val origin: String,
    val specie: String,
    val imgUrl: String
)