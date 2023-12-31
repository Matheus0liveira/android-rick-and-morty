package com.matheus0liveira.rickandmorty.presenter

import android.util.Log
import com.matheus0liveira.rickandmorty.data.CharacterCallback
import com.matheus0liveira.rickandmorty.data.CharacterDataSource
import com.matheus0liveira.rickandmorty.data.CharacterDetailsCallback
import com.matheus0liveira.rickandmorty.model.Character
import com.matheus0liveira.rickandmorty.model.CharacterAPI
import com.matheus0liveira.rickandmorty.model.CharacterDetails
import com.matheus0liveira.rickandmorty.model.Info

class CharacterPresenter(
    private val callback: CharacterView,
    private val dataSource: CharacterDataSource = CharacterDataSource(),
) {
    fun findAllCharacters(currentPage: Int = 1, searchName: String? = "") {
        callback.showProgress()
        dataSource.findAllCharacters(currentPage, searchName, object : CharacterCallback {
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
                callback.hideProgress()
            }

            override fun onError(message: String) {
                callback.showError(message)
                callback.hideProgress()
            }

        })
    }

    fun findCharacterBy(id: Int) {
        callback.showProgress()
        dataSource.findCharacterBy(id, object : CharacterDetailsCallback {
            override fun onSuccess(characterDetails: CharacterDetails) {
                callback.showCharacter(characterDetails)
                callback.hideProgress()
            }

            override fun onError(message: String) {
                callback.showError(message)
                callback.hideProgress()
            }

        })
    }


}