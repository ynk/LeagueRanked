package com.ynk.leagueranked.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.ynk.leagueranked.databinding.ListItemPlayerBinding

import com.ynk.leagueranked.api.local.model.Player

/**
 * Player adapter
 *
 * @property playerClickListener
 * @constructor Create empty Player adapter
 */
class PlayerAdapter(private var playerClickListener: PlayerClickListener): ListAdapter<Player, PlayerViewHolder>(Callback())  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = ListItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PlayerViewHolder(binding)
    }

    override fun onBindViewHolder(playerHolder: PlayerViewHolder, position: Int) {
        val player = getItem(position)
        playerHolder.bindData(player)
        playerHolder.itemView.setOnClickListener {
            playerClickListener.onPlayerClicked(player
            )
        }

    }




}
private class Callback: DiffUtil.ItemCallback<Player>(){
    override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem.revisionDate == newItem.revisionDate
    }
    override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem.revisionDate == newItem.revisionDate
    }


}


/**
 * Player view holder
 *
 * @property binding
 * @constructor Create empty Player view holder
 */
class PlayerViewHolder(private var binding:ListItemPlayerBinding): RecyclerView.ViewHolder(binding.root)
{
    /**
     * Bind data
     *
     * @param player, Glide will take care of binding the image to the image in xml
     */
    fun bindData(player: Player){
        Glide.with(binding.root)
            .load(player.profileIconId)
            .transform(CircleCrop())
            .into(binding.playerImage)
        binding.player = player



    }
}

/**
 * Player click listener
 *
 * @constructor Create empty Player click listener
 */
interface PlayerClickListener{
    /**
     * On player clicked
     *
     * @param player
     */
    fun onPlayerClicked(player: Player)
}