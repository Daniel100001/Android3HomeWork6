package com.example.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ItemEpisodeBinding
import com.example.rickandmorty.models.EpisodeModel

class EpisodeAdapter :
    PagingDataAdapter<EpisodeModel, EpisodeAdapter.ViewHolder>(DiffUtilCallback()) {

    class ViewHolder(private val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(episodeModel: EpisodeModel?) {
            binding.itemCharacterName.text = episodeModel?.name
            binding.itemLocationEpisode.text = episodeModel?.episode
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemEpisodeBinding.inflate(
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

    class DiffUtilCallback : DiffUtil.ItemCallback<EpisodeModel>() {

        override fun areItemsTheSame(oldItem: EpisodeModel, newItem: EpisodeModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EpisodeModel, newItem: EpisodeModel): Boolean {
            return oldItem == newItem
        }
    }
}