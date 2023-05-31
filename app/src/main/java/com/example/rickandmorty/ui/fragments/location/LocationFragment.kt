package com.example.rickandmorty.ui.fragments.location

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.ConnectionLiveData
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentLocationBinding
import com.example.rickandmorty.ui.activity.MainActivity
import com.example.rickandmorty.ui.adapters.LocationAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationFragment :
    BaseFragment<FragmentLocationBinding, LocationViewModel>(R.layout.fragment_location) {

    override val binding by viewBinding(FragmentLocationBinding::bind)
    override val viewModel: LocationViewModel by viewModels()
    private val locationAdapter = LocationAdapter(this::onItemClick)
    private lateinit var connectionLiveData: ConnectionLiveData


    private fun onItemClick(args: Int) {
        findNavController().navigate(
            LocationFragmentDirections.actionLocationFragmentToLocationDetailFragment(
                args
            )
        )
    }

    override fun initialize() {
        binding.locationRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = locationAdapter
        }
    }

    override fun setUpObserves() {
        connectionLiveData = activity?.application?.let { ConnectionLiveData(application = it) }!!
        connectionLiveData.observe(viewLifecycleOwner){
            if (it) {
                viewModel.fetchCharacters().observe(viewLifecycleOwner) { locationModel ->
                    locationAdapter.submitList(locationModel.results)
                    Toast.makeText(requireContext(), "Wife is connected", Toast.LENGTH_SHORT).show()
                }
            } else {
                viewModel.getAll().observe(viewLifecycleOwner) {locationModelList ->
                    locationAdapter.submitList(locationModelList)
                    Toast.makeText(requireContext(), "Wife is not have - l", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    override fun bottomNavigationSelected() {
        (requireActivity() as MainActivity).setOnItemReselectedListener {
            binding.locationRecyclerView.smoothScrollToPosition(0)
        }
    }
}
