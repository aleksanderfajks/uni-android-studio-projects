package com.example.foodapp

import android.util.Log

/**
 * Singleton do zarządzania koszykiem zamówień.
 */
object CartManager {
    private const val TAG = "FoodOrder"
    private val cartItems = mutableListOf<MenuItem>()

    fun addItem(item: MenuItem) {
        cartItems.add(item)
        Log.d(TAG, "Dodano do koszyka: ${item.name}")
    }

    fun getItems(): List<MenuItem> = cartItems

    fun getTotalPrice(): Double = cartItems.sumOf { it.price }

    fun clearCart() {
        cartItems.clear()
    }

    fun isEmpty(): Boolean = cartItems.isEmpty()

    // Metody do zachowania stanu (używane w CartFragment)
    fun saveState(): List<MenuItem> {
        return ArrayList(cartItems)
    }

    fun restoreState(items: List<MenuItem>) {
        cartItems.clear()
        cartItems.addAll(items)
    }
}
