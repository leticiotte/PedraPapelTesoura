package com.example.pedrapapeltesoura.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.pedrapapeltesoura.databinding.FragmentStartGameBinding

class StartGameFragment  : Fragment() {
    private var _binding: FragmentStartGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var twoPlayersBtn: Button
    private lateinit var threePlayersBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartGameBinding.inflate(inflater, container, false)

        setupInputs()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupInputs() {
        twoPlayersBtn = binding.twoPlayersBtn
        threePlayersBtn = binding.threePlayersBtn
    }

}