package com.ynk.leagueranked.api.local.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Player
 *
 * @property id
 * @property level
 * @property username
 * @property solo
 * @property flex
 * @property revisionDate
 * @property utc
 * @property ingame
 * @property profileIconId@
 * rofileIconId
 * @property server
 * @constructor Create empty Player
 */
@Entity(tableName = "players")
data class Player (
    @PrimaryKey
    val id: String,
    val level: Int,
    val username: String,
    val solo: String,
    val flex: String,
    val revisionDate: Long,
    val utc: String,
    val ingame: Boolean? = false,
    val profileIconId: String? = "29", // 29 is a default helmet icon
    val server: String,
) {


}