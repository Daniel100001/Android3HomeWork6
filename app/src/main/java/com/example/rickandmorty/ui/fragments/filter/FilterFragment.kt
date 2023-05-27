package com.example.rickandmorty.ui.fragments.filter

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmorty.R
import com.example.rickandmorty.base.BaseFragment
import com.example.rickandmorty.databinding.FragmentFilterBinding

class FilterFragment : BaseFragment<FragmentFilterBinding, FilterFragmentViewModel>(R.layout.fragment_filter) {

    override val binding by viewBinding(FragmentFilterBinding::bind)
    override val viewModel : FilterFragmentViewModel by viewModels()

    override fun setUpListeners() {
        binding.ready.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val status = binding.editTextStatus.text.toString()
            val species = binding.editTextSpecies.text.toString()
            val type = binding.editTextType.text.toString()
            val gender = binding.editTextGender.text.toString()

            val bundle = Bundle()
            bundle.putString("name", name)
            bundle.putString("status", status)
            bundle.putString("species", species)
            bundle.putString("type", type)
            bundle.putString("gender", gender)

            findNavController().navigate(
                R.id.action_filterFragment_to_characterFragment,
                bundle
            )
        }
    }
}