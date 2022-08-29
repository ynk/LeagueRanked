package com.ynk.leagueranked.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ynk.leagueranked.R

/**
 * Main activity
 *
 * @constructor Create empty Main activity
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}