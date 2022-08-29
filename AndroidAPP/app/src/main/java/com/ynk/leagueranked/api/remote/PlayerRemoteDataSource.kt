package com.ynk.leagueranked.api.remote

/**
 * Player remote data source
 *
 * @property apiService
 * @constructor Create empty Player remote data source
 */
class PlayerRemoteDataSource(
    private val apiService: PlayerApiService
): BaseDataSource()
{

    /**
     * Get players
     *
     */
    suspend fun getPlayers() = getResult {apiService.getPlayers()}


}
