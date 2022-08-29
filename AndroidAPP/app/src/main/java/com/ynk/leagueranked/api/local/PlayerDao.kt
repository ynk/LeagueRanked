package com.ynk.leagueranked.api.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ynk.leagueranked.api.local.model.Player

/**
 * Player dao
 *
 * @constructor Create empty Player dao
 */
@Dao
interface PlayerDao {
    /**
     * Get all players
     *
     * @return
     */
    @Transaction
    @Query("SELECT * FROM players")
    fun getAllPlayers(): LiveData<List<Player>>


    /**
     * Get player
     *
     * @param id
     * @return
     */
    @Query("SELECT * FROM players WHERE id = :id")
    fun getPlayer(id:String): LiveData<Player>


    /**
     * Insert all
     *
     * @param list
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Player>)


}