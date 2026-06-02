package com.example.lab72

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class FifthFragment : Fragment(R.layout.fragment_fifth) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        view.findViewById<Button>(R.id.btn_to_fourth).setOnClickListener {
            findNavController().navigate(R.id.action_fifthFragment_to_fourthFragment)
        }
    }
}