package com.matheus0liveira.rickandmorty.presenter

import com.matheus0liveira.rickandmorty.model.Character
import com.matheus0liveira.rickandmorty.model.CharacterDetails
import com.matheus0liveira.rickandmorty.model.Info

interface CharacterView {

    fun showCharacter(character: List<Character>) {}
    fun showCharacter(characterDetails: CharacterDetails) {}
    fun handleInfo(info: Info) {}
    fun showProgress()
    fun hideProgress()
    fun showError(message: String)
}