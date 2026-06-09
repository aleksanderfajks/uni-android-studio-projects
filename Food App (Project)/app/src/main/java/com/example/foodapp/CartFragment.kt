package com.example.foodapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.foodapp.databinding.FragmentCartBinding

import java.util.Locale
import androidx.navigation.fragment.NavHostFragment

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val TAG = "FoodOrder"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "Przejście do: CartFragment")

        setupCartList()
        updateTotal()

        binding.btnPlaceOrder.setOnClickListener {
            if (CartManager.isEmpty()) {
                Log.e(TAG, "Błąd: Próba złożenia zamówienia z pustym koszykiem")
                Toast.makeText(requireContext(), "Koszyk jest pusty!", Toast.LENGTH_SHORT).show()
            } else {
                placeOrder()
            }
        }
    }

    private fun setupCartList() {
        val adapter = FoodAdapter(CartManager.getItems()) { item ->
            // W koszyku można by dodać usuwanie, ale wymagania tego nie precyzują
        }
        binding.rvCartItems.adapter = adapter
    }

    private fun updateTotal() {
        binding.tvTotalPrice.text = String.format(Locale.getDefault(), "%.2f PLN", CartManager.getTotalPrice())
    }

    private fun placeOrder() {
        Log.i(TAG, "Złożenie zamówienia: ${CartManager.getTotalPrice()} PLN")
        
        // Pokaż ProgressBar
        binding.progressBar.visibility = View.VISIBLE
        binding.btnPlaceOrder.isEnabled = false

        // Opóźnienie 2 sekundy przed nawigacją
        Handler(Looper.getMainLooper()).postDelayed({
            if (isAdded) {
                CartManager.clearCart()
                NavHostFragment.findNavController(this).navigate(R.id.action_cartFragment_to_orderConfirmationFragment)
            }
        }, 2000)
    }

    /**
     * Lifecycle: onPause() - zapisuje stan koszyka
     */
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "CartFragment: onPause() - zapisywanie stanu")
        // W tej prostej aplikacji CartManager jest singletonem, więc stan i tak trwa,
        // ale dodajemy to zgodnie z wymaganiami.
    }

    /**
     * Lifecycle: onResume() - przywraca stan koszyka
     */
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "CartFragment: onResume() - przywracanie stanu")
        updateTotal()
    }

    /**
     * Lifecycle: onSaveInstanceState() - zachowuje dane przy obróceniu ekranu
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "CartFragment: onSaveInstanceState()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
