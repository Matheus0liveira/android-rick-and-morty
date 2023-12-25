package com.matheus0liveira.rickandmorty.presenter

import com.matheus0liveira.rickandmorty.model.Character

interface CharacterView {

    fun showCharacter(character: List<Character>)
    fun showProgress()
    fun hideProgress()
    fun showError(message: String)
}