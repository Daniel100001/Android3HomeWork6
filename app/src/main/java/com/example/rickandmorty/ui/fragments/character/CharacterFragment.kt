package com.example.rickandmorty.ui.fragments.character

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.ConnectionLiveData
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentCharacterBinding
import com.example.rickandmorty.ui.activity.MainActivity
import com.example.rickandmorty.ui.adapters.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment :
    BaseFragment<FragmentCharacterBinding, CharacterViewModel>(R.layout.fragment_character) {

    override val binding by viewBinding(FragmentCharacterBinding::bind)
    override val viewModel by activityViewModels<CharacterViewModel>()
    private val characterAdapter = CharacterAdapter(this::onItemClick)
    private lateinit var connectionLiveData: ConnectionLiveData

    private fun onItemClick(args: Int) {
        findNavController().navigate(
            CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment(
                args
            )
        )
    }

    override fun initialize() {
        binding.characterRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = characterAdapter
        }
    }

    override fun setUpObserves() {
        connectionLiveData = activity?.application?.let { ConnectionLiveData(application = it) }!!
        connectionLiveData.observe(viewLifecycleOwner){
            if (it) {
                viewModel.fetchCharacters().observe(viewLifecycleOwner) { characterModel ->
                    characterAdapter.submitList(characterModel.results)
                    Toast.makeText(requireContext(), "Wife is connected", Toast.LENGTH_SHORT).show()
                }
            } else {
                viewModel.getAll()?.observe(viewLifecycleOwner) {characterModelList ->
                    characterAdapter.submitList(characterModelList)
                    Toast.makeText(requireContext(), "Wife is not have - l", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    override fun setUpListeners() {
        binding.buttonMore.setOnClickListener {
            findNavController().navigate(R.id.filterFragment)
        }
    }

    override fun bottomNavigationSelected() {
        (requireActivity() as MainActivity).setOnItemReselectedListener {
            binding.characterRecyclerView.smoothScrollToPosition(0)
        }
    }
}

