package com.example.rickandmorty.ui.fragments.location.detail

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentLocationDetailBinding
import com.example.rickandmorty.ui.fragments.location.LocationViewModel
import kotlinx.coroutines.launch

class LocationDetailFragment :
    BaseFragment<FragmentLocationDetailBinding, LocationViewModel>(R.layout.fragment_location_detail) {

    override val binding by viewBinding(FragmentLocationDetailBinding::bind)
    override val viewModel by activityViewModels<LocationViewModel>()
    private val args by navArgs<LocationDetailFragmentArgs>()

    override fun initialize() {
        lifecycleScope.launch {
            viewModel.fetchSingleCharacter(args.id).collect {
                binding.name.text = it?.name

            }
        }
    }
}