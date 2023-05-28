package com.example.rickandmorty.ui.fragments.location


import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentLocationBinding
import com.example.rickandmorty.ui.activity.MainActivity
import com.example.rickandmorty.ui.adapters.LocationAdapter
import kotlinx.coroutines.launch

class LocationFragment : BaseFragment<FragmentLocationBinding,LocationViewModel>(R.layout.fragment_location) {

    override val binding by viewBinding(FragmentLocationBinding::bind)
    override val viewModel: LocationViewModel by viewModels()
    private val locationAdapter = LocationAdapter()

    override fun initialize() {
        binding.locationRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = locationAdapter
        }
    }

    override fun setUpObserves() {
        lifecycleScope.launch {
            viewModel.fetchLocation().collect{
                locationAdapter.submitData(it)
            }
        }
    }

    override fun bottomNavigationSelected() {
        (requireActivity() as MainActivity).setOnItemReselectedListener{
            binding.locationRecyclerView.smoothScrollToPosition(0)
        }
    }
}
