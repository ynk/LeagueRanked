package com.ynk.leagueranked.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.ynk.leagueranked.api.repo.RepositoryUtils
import com.ynk.leagueranked.databinding.FragmentPlayerDetailBinding
import com.ynk.leagueranked.viewmodels.factory.PlayerDetailViewModelFactory
import com.ynk.leagueranked.viewmodels.models.PlayerDetailViewModel

/**
 * Player detail fragment
 *
 * @constructor Create empty Player detail fragment
 */
class PlayerDetailFragment:Fragment() {


    private val arguments: PlayerDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlayerDetailBinding.inflate(inflater,container,false)
        val factory = PlayerDetailViewModelFactory(RepositoryUtils.createPlayerRepository(requireContext()))
        val viewModel = ViewModelProvider(this,factory).get(PlayerDetailViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner


        viewModel.player.observe(viewLifecycleOwner, Observer {
            binding.player = it
            Glide.with(binding.root)
                .load(it.profileIconId)
                .transform(CircleCrop())
                .into(binding.playerImage)
         })

        viewModel.updatePlayer(arguments.id)



        return binding.root

    }



}