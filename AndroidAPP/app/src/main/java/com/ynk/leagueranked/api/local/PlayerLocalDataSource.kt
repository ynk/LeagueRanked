package com.ynk.leagueranked.api.local

import com.ynk.leagueranked.api.local.model.Player

/**
 * Player local data source
 *
 * @property playerDao
 * @constructor Create empty Player local data source
 */
class PlayerLocalDataSource(private val playerDao: PlayerDao) {

    /**
     * Get all players
     *
     */
    fun getAllPlayers() = playerDao.getAllPlayers()

    /**
     * Get player
     *
     * @param id
     */
    fun getPlayer(id: String) = playerDao.getPlayer(id)


    /**
     * Insert all
     *
     * @param result
     */
    suspend fun insertAll(result: List<Player>) {
        playerDao.insertAll(result)
    }


}