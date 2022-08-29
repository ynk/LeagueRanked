package com.ynk.leagueranked.viewmodels.models

import androidx.lifecycle.ViewModel

import com.ynk.leagueranked.api.repo.PlayerRepository


/**
 * Player overview view model
 *
 * @constructor
 *
 * @param repository
 */
class PlayerOverviewViewModel(repository:PlayerRepository):ViewModel() {
     var players = repository.getPlayers()

}