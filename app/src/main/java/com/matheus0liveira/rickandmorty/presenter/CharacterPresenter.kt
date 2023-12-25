package com.matheus0liveira.rickandmorty.presenter

import android.util.Log
import com.matheus0liveira.rickandmorty.data.CharacterCallback
import com.matheus0liveira.rickandmorty.data.CharacterDataSource
import com.matheus0liveira.rickandmorty.model.Character
import com.matheus0liveira.rickandmorty.model.CharacterAPI
import com.matheus0liveira.rickandmorty.model.Info

class CharacterPresenter(
    private val callback: CharacterView,
    private val dataSource: CharacterDataSource = CharacterDataSource(),
) {
    fun findAllCharacters(currentPage: Int = 1) {

        dataSource.findAllCharacters(currentPage, object : CharacterCallback {
            override fun onSuccess(characters: CharacterAPI, info: Info) {
                val character = characters.results.map {
                    Character(
                        id = it.id,
                        name = it.name,
                        status = it.status,
                        origin = it.origin.name,
                        specie = it.species,
                        imgUrl = it.image
                    )
                }
                callback.showCharacter(character)
                callback.handleInfo(info)
            }

            override fun onError(message: String) = callback.showError(message)

        })
    }


}