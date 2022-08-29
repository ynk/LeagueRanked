package com.ynk.leagueranked.api.repo


import com.ynk.leagueranked.api.local.PlayerLocalDataSource
import com.ynk.leagueranked.api.remote.PlayerRemoteDataSource
import com.ynk.leagueranked.utils.performGetOperation

/**
 * Player repository
 *
 * @property playerLocalDataSource
 * @property playerRemoteDataSource
 * @constructor Create empty Player repository
 */
class PlayerRepository(private val playerLocalDataSource: PlayerLocalDataSource, private val playerRemoteDataSource: PlayerRemoteDataSource) {

    /**
     * Get player
     *
     * @param id
     */
    fun  getPlayer(id:String) = playerLocalDataSource.getPlayer(id)

    /**
     * Get players
     *
     */
    fun getPlayers() = performGetOperation(
        databaseQuery = {playerLocalDataSource.getAllPlayers()},
        networkCall = {playerRemoteDataSource.getPlayers()},
        saveCallResult = {playerLocalDataSource.insertAll(it.result)}
    )
}