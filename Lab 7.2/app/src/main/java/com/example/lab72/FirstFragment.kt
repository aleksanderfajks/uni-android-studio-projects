package com.example.lab72

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class FirstFragment : Fragment(R.layout.fragment_first) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        view.findViewById<Button>(R.id.btn_to_second).setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
        
        view.findViewById<Button>(R.id.btn_to_fifth).setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_fifthFragment)
        }
    }
}