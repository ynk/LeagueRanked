package com.ynk.leagueranked.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Bind recycler view adapter
 *
 * @param adapter
 */
@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>){
    this.run {
        this.adapter = adapter
    }
}