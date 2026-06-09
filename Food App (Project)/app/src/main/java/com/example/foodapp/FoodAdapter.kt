package com.example.foodapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.databinding.ItemFoodBinding

import java.util.Locale

class FoodAdapter(
    private val items: List<MenuItem>,
    private val onAddClick: (MenuItem) -> Unit
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvFoodName.text = item.name
        holder.binding.tvFoodDesc.text = item.description
        holder.binding.tvFoodPrice.text = String.format(Locale.getDefault(), "%.2f PLN", item.price)
        holder.binding.btnAddToCart.setOnClickListener { onAddClick(item) }
    }

    override fun getItemCount(): Int = items.size
}
