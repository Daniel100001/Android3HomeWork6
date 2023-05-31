package com.example.rickandmorty.ui.fragments.episode

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
import com.example.rickandmorty.databinding.FragmentEpisodeBinding
import com.example.rickandmorty.ui.activity.MainActivity
import com.example.rickandmorty.ui.adapters.EpisodeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeFragment :
    BaseFragment<FragmentEpisodeBinding, EpisodeViewModel>(R.layout.fragment_episode) {

    override val binding by viewBinding(FragmentEpisodeBinding::bind)
    override val viewModel: EpisodeViewModel by viewModels()
    private val episodeAdapter = EpisodeAdapter(this::onItemClick)

    private fun onItemClick(args : Int) {
        findNavController().navigate(EpisodeFragmentDirections.actionEpisodeFragmentToEpisodeFragmentDetail(args))
    }

    private lateinit var connectionLiveData: ConnectionLiveData



    override fun initialize() {
        binding.episodeRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = episodeAdapter
        }
    }

    override fun setUpObserves() {
        connectionLiveData = activity?.application?.let { ConnectionLiveData(application = it) }!!
        connectionLiveData.observe(viewLifecycleOwner){
            if (it) {
                viewModel.fetchCharacters().observe(viewLifecycleOwner) { episodeModel ->
                    episodeAdapter.submitList(episodeModel.results)
                    Toast.makeText(requireContext(), "Wife is connected", Toast.LENGTH_SHORT).show()
                }
            } else {
                viewModel.getAll().observe(viewLifecycleOwner) {episodeModelList ->
                    episodeAdapter.submitList(episodeModelList)
                    Toast.makeText(requireContext(), "Wife is not have - l", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    override fun bottomNavigationSelected() {
        (requireActivity() as MainActivity).setOnItemReselectedListener {
            binding.episodeRecyclerView.smoothScrollToPosition(0)
        }
    }
}