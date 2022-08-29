package com.ynk.leagueranked.api.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ynk.leagueranked.api.local.model.Player

/**
 * App database
 *
 * @constructor Create empty App database
 */
@Database(entities = [Player::class],version = 1,exportSchema = false)
abstract class AppDatabase:RoomDatabase() {

    /**
     * Player dao
     *
     * @return
     */
    abstract fun playerDao():PlayerDao


    companion object {
        @Volatile private var instance: AppDatabase? = null
        fun getDatabase(context: Context):AppDatabase = synchronized(this){ instance ?: buildDatabase(context).also{instance = it}}

        private fun buildDatabase(appContext: Context) = Room.databaseBuilder(appContext, AppDatabase::class.java, "playersDB").fallbackToDestructiveMigration().build()

    }
}