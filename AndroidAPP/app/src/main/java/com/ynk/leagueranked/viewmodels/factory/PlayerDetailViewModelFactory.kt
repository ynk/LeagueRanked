package com.ynk.leagueranked.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ynk.leagueranked.api.repo.PlayerRepository
import com.ynk.leagueranked.viewmodels.models.PlayerDetailViewModel

import java.lang.IllegalArgumentException

/**
 * Player detail view model factory
 *
 * @property repository
 * @constructor Create empty Player detail view model factory
 */
class PlayerDetailViewModelFactory(private val repository: PlayerRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PlayerDetailViewModel::class.java)) return PlayerDetailViewModel(repository) as T
        throw IllegalArgumentException("this kapot")
    }

}