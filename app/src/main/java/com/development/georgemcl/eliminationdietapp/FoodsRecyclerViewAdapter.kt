package com.development.georgemcl.eliminationdietapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.development.georgemcl.eliminationdietapp.databinding.RecyclerFoodBinding
import com.development.georgemcl.eliminationdietapp.objects.Food

class FoodsRecyclerViewAdapter(
    private val listener: FoodSelectedListener)
    : ListAdapter<Food, FoodsRecyclerViewAdapter.ViewHolder>(FoodsDiffCallback()) {

    class ViewHolder(private val binding: RecyclerFoodBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Food, listener: FoodSelectedListener) {
            binding.food = item
            binding.listener = listener
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder{
                val layoutInflater = LayoutInflater.from((parent.context))
                val binding = RecyclerFoodBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }
}

class FoodsDiffCallback: DiffUtil.ItemCallback<Food>() {
    override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem.name == newItem.name
    }
    override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem == newItem
    }
}

interface FoodSelectedListener {
    fun foodSelected(food: Food)
}