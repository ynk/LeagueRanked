package com.ynk.leagueranked.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ynk.leagueranked.api.repo.PlayerRepository
import com.ynk.leagueranked.viewmodels.models.PlayerOverviewViewModel
import java.lang.IllegalArgumentException

/**
 * Player overview view model factory
 *
 * @property repository
 * @constructor Create empty Player overview view model factory
 */
class PlayerOverviewViewModelFactory(private val repository: PlayerRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PlayerOverviewViewModel::class.java)){
            return PlayerOverviewViewModel(repository) as T
        }
        throw IllegalArgumentException("Kapot")
    }

}