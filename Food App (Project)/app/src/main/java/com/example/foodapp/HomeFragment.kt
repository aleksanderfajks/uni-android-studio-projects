package com.example.foodapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.foodapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val TAG = "FoodOrder"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "Przejście do: HomeFragment")

        // Przycisk "Przeglądaj menu" - nawigacja do MenuFragment
        binding.btnBrowseMenu.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_menuFragment)
        }

        // Przycisk "O nas" - Jawna intencja (Explicit Intent) do AboutActivity
        binding.btnAbout.setOnClickListener {
            val intent = Intent(requireContext(), AboutActivity::class.java)
            startActivity(intent)
        }

        // Animacja pojawiania się przycisków z opóźnieniem 500ms
        binding.btnBrowseMenu.postDelayed({
            binding.btnBrowseMenu.visibility = View.VISIBLE
            binding.btnBrowseMenu.alpha = 0f
            binding.btnBrowseMenu.animate().alpha(1f).setDuration(500).start()
        }, 500)

        binding.btnAbout.postDelayed({
            binding.btnAbout.visibility = View.VISIBLE
            binding.btnAbout.alpha = 0f
            binding.btnAbout.animate().alpha(1f).setDuration(500).start()
        }, 800)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
