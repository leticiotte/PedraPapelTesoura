package com.example.pedrapapeltesoura.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pedrapapeltesoura.R
import com.example.pedrapapeltesoura.databinding.FragmentRoundBinding
import com.example.pedrapapeltesoura.domain.model.interfaces.ToolbarConfig
import com.google.android.material.appbar.MaterialToolbar

class RoundFragment : Fragment(), ToolbarConfig {
    private var _binding: FragmentRoundBinding? = null
    private val binding get() = _binding!!
    private lateinit var toolbar: MaterialToolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoundBinding.inflate(inflater, container, false)

        setupInputs()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findAndLinkToolbar()
        setupNavigationOnClickListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupInputs() {
    }

    override fun findAndLinkToolbar() {
        toolbar = requireActivity().findViewById(R.id.toolbar)
    }

    override fun setupNavigationOnClickListener() {
        toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_RoundFragment_to_StartGameFragment)
        }
    }
}