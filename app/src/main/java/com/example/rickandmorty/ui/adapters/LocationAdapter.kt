package com.example.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ItemLocationBinding
import com.example.rickandmorty.models.LocationModel

class LocationAdapter(private val onItemClick: (id: Int) -> Unit) :
    ListAdapter<LocationModel, LocationAdapter.ViewHolder>(DiffUtilCallback()) {

    inner class ViewHolder(private val binding: ItemLocationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClick(absoluteAdapterPosition)
            }
        }

        fun onBind(locationModel: LocationModel?) {
            binding.itemCharacterName.text = locationModel?.name
            binding.itemLocationType.text = locationModel?.type
            binding.itemLocationDimension.text = locationModel?.dimension
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLocationBinding.inflate(
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

    class DiffUtilCallback : DiffUtil.ItemCallback<LocationModel>() {

        override fun areItemsTheSame(oldItem: LocationModel, newItem: LocationModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LocationModel, newItem: LocationModel): Boolean {
            return oldItem == newItem
        }
    }
}