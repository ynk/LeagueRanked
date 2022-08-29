package com.ynk.leagueranked.api.remote

import com.ynk.leagueranked.api.local.model.Player

/**
 * Get player api response
 *
 * @property result
 * @constructor Create empty Get player api response
 */
data class GetPlayerApiResponse (
    val result: List<Player>
)