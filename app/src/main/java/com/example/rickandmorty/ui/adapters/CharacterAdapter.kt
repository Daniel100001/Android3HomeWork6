package com.example.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.models.CharacterModel

class CharacterAdapter(private val onItemClick: (id: Int) -> Unit) :
    PagingDataAdapter<CharacterModel, CharacterAdapter.ViewHolder>(DiffUtilCallback()) {

    inner class ViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClick(absoluteAdapterPosition)
            }
        }

        fun onBind(characterModel: CharacterModel?) {
            Glide.with(binding.itemCharacterImage).load(characterModel?.image)
                .into(binding.itemCharacterImage)
            binding.itemCharacterName.text = characterModel?.name
            binding.itemCharacterSpecies.text = characterModel?.species
            binding.itemCharacterStatus.text = characterModel?.status
            binding.location.text = characterModel?.type
            binding.gender.text = characterModel?.gender
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<CharacterModel>() {

        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem == newItem
        }
    }
}