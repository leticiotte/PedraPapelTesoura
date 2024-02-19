package com.example.pedrapapeltesoura.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pedrapapeltesoura.R
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
        setupButtonsOnClickListener()
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

    private fun setupButtonsOnClickListener() {
        setupTwoPlayersBtnOnClickListener()
        setupThreePlayersBtnOnClickListener()
    }

    private fun setupTwoPlayersBtnOnClickListener() {
        twoPlayersBtn.setOnClickListener {
            openRoundFragment(2)
        }
    }

    private fun setupThreePlayersBtnOnClickListener() {
        threePlayersBtn.setOnClickListener {
            openRoundFragment(3)
        }
    }

    private fun openRoundFragment(playersQuantity: Int) {
        val bundle = Bundle()
        bundle.putSerializable("playersQuantity", playersQuantity)
        findNavController().navigate(R.id.action_StartGameFragment_to_RoundFragment, bundle)
    }

}