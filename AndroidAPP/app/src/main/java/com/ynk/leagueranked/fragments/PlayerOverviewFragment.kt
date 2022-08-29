package com.ynk.leagueranked.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController


import com.ynk.leagueranked.adapters.PlayerAdapter
import com.ynk.leagueranked.adapters.PlayerClickListener
import com.ynk.leagueranked.api.repo.RepositoryUtils
import com.ynk.leagueranked.databinding.FragmentPlayerOverviewBinding
import com.ynk.leagueranked.api.local.model.Player
import com.ynk.leagueranked.viewmodels.factory.PlayerOverviewViewModelFactory
import com.ynk.leagueranked.viewmodels.models.PlayerOverviewViewModel


/**
 * Player overview fragment
 *
 * @constructor Create empty Player overview fragment
 */
class PlayerOverviewFragment: Fragment(),PlayerClickListener {

    private lateinit var viewModel: PlayerOverviewViewModel
    private lateinit var adapter: PlayerAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPlayerOverviewBinding.inflate(inflater,container,false)
        val factory = PlayerOverviewViewModelFactory(RepositoryUtils.createPlayerRepository(requireContext()))
        viewModel = ViewModelProvider(this,factory).get(PlayerOverviewViewModel::class.java)


        binding.lifecycleOwner = viewLifecycleOwner

        adapter = PlayerAdapter(this)

        binding.adapter = adapter


        viewModel.players.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.data)

        })


        return binding.root
    }

    override fun onPlayerClicked(player: Player) {
        val directions = PlayerOverviewFragmentDirections.actionPlayerOverviewFragmentToPlayerDetailFragment(player.id)
        findNavController().navigate(directions)
    }
}