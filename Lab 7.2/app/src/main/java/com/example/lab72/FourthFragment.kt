package com.example.lab72

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class FourthFragment : Fragment(R.layout.fragment_fourth) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        view.findViewById<Button>(R.id.btn_back_to_first).setOnClickListener {
            findNavController().navigate(R.id.action_fourthFragment_to_firstFragment)
        }
    }
}