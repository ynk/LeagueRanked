package com.ynk.leagueranked.viewmodels.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.ynk.leagueranked.api.repo.PlayerRepository
import com.ynk.leagueranked.api.local.model.Player

/**
 * Player detail view model
 *
 * @property repository, de repo
 * @constructor Create empty Player detail view model
 */
class PlayerDetailViewModel(private val repository:PlayerRepository):ViewModel()   {

    private val _id = MutableLiveData<String>()

    private val _player = _id.switchMap { id ->
        repository.getPlayer(id)
    }
    val player: LiveData<Player> = _player

    /**
     * Update player
     *
     * @param id
     */
    fun updatePlayer(id: String) {
        _id.value = id
    }

}