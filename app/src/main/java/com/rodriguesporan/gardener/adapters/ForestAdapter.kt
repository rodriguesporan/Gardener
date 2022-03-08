package com.rodriguesporan.gardener.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rodriguesporan.gardener.R
import com.rodriguesporan.gardener.data.PlantUiState
import com.rodriguesporan.gardener.adapters.ForestAdapter.PlantViewHolder

class ForestAdapter : ListAdapter<PlantUiState, PlantViewHolder>(DiffCallback) {

    class PlantViewHolder(itemView: View) : ViewHolder(itemView) {

        fun bind(plantUiState: PlantUiState) {
            val tvPlantName by lazy { itemView.findViewById<TextView>(R.id.text_view_plant_name) }
            tvPlantName.text = plantUiState.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.forest_fragment_item, parent, false)

        return PlantViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val plant = getItem(position)
        holder.bind(plant)
    }

    companion object DiffCallback : ItemCallback<PlantUiState>() {
        override fun areItemsTheSame(oldItem: PlantUiState, newItem: PlantUiState): Boolean {
            return oldItem.plantId == newItem.plantId
        }

        override fun areContentsTheSame(oldItem: PlantUiState, newItem: PlantUiState): Boolean {
            return oldItem.name == newItem.name
        }
    }
}
