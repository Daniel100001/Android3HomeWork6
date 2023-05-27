package com.example.rickandmorty.ui.fragments.character

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentCharacterBinding
import com.example.rickandmorty.models.CharacterModel
import com.example.rickandmorty.ui.adapters.CharacterAdapter
import kotlinx.coroutines.launch

class CharacterFragment : BaseFragment<FragmentCharacterBinding, SharedViewModel>(R.layout.fragment_character) {

    override val binding by viewBinding(FragmentCharacterBinding::bind)
    override val viewModel : SharedViewModel by viewModels()
    private val characterAdapter = CharacterAdapter(this::onItemClick)

    private fun onItemClick(args: CharacterModel) {
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
        lifecycleScope.launch {
            viewModel.fetchCharacters().collect{
                characterAdapter.submitData(it)
            }
        }
    }

    override fun setUpListeners() {
        binding.buttonMore.setOnClickListener {
            findNavController().navigate(R.id.filterFragment)
        }
    }
}
