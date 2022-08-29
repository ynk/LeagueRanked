package com.ynk.leagueranked.api.repo

import android.content.Context
import com.ynk.leagueranked.api.remote.PlayerApi
import com.ynk.leagueranked.api.local.AppDatabase
import com.ynk.leagueranked.api.local.PlayerLocalDataSource
import com.ynk.leagueranked.api.remote.PlayerRemoteDataSource

/**
 * Repository utils
 *
 * @constructor Create empty Repository utils
 */
class RepositoryUtils {
    companion object{
        fun createPlayerRepository(context: Context):PlayerRepository{
            val database = AppDatabase.getDatabase(context)
            val localDataSource = PlayerLocalDataSource(database.playerDao())
            val remoteDataSource = PlayerRemoteDataSource(PlayerApi.apiService)

            return PlayerRepository(localDataSource,remoteDataSource)
        }
    }
}