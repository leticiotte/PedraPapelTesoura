package com.example.pedrapapeltesoura.ui.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pedrapapeltesoura.R
import com.example.pedrapapeltesoura.databinding.FragmentThreePlayersRoundBinding
import com.example.pedrapapeltesoura.domain.model.interfaces.ToolbarConfig
import com.google.android.material.appbar.MaterialToolbar

class RoundThreePlayersFragment : Fragment(), ToolbarConfig {
    private var _binding: FragmentThreePlayersRoundBinding? = null
    private val binding get() = _binding!!
    private lateinit var toolbar: MaterialToolbar
    private lateinit var gameOptionsNestedScrollView: NestedScrollView
    private lateinit var downCounterConstraintLayout: ConstraintLayout
    private lateinit var roundResultNestedScrollView: NestedScrollView
    private lateinit var downCounterTv: TextView
    private lateinit var resultTitleTv: TextView
    private lateinit var rockImg: ImageView
    private lateinit var paperImg: ImageView
    private lateinit var scissorImg: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThreePlayersRoundBinding.inflate(inflater, container, false)

        setupInputs()
        setupLayouts()
        setupImageViews()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showGameOptionsNestedScrollView()
        findAndLinkToolbar()
        setupNavigationOnClickListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun findAndLinkToolbar() {
        toolbar = requireActivity().findViewById(R.id.toolbar)
    }

    override fun setupNavigationOnClickListener() {
        toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_RoundThreePlayersFragment_to_StartGameFragment)
        }
    }

    private fun setupLayouts() {
        gameOptionsNestedScrollView = binding.gameOptions3PNestedScrollView
        downCounterConstraintLayout = binding.downCounter3PConstraintLayout
        roundResultNestedScrollView = binding.roundResult3PNestedScrollView
    }

    private fun setupInputs() {
        downCounterTv = binding.downCounter3PTv
        resultTitleTv = binding.resultTitle3PTv
    }

    private fun setupImageViews() {
        rockImg = binding.rock3PImg
        paperImg = binding.paper3PImg
        scissorImg = binding.scissor3PImg


        rockImg.setOnClickListener {
            showDownCounterConstraintLayout()
        }

        paperImg.setOnClickListener {
            showDownCounterConstraintLayout()
        }

        scissorImg.setOnClickListener {
            showDownCounterConstraintLayout()
        }
    }

    private fun showGameOptionsNestedScrollView(){
        gameOptionsNestedScrollView.visibility = View.VISIBLE
        downCounterConstraintLayout.visibility = View.GONE
        roundResultNestedScrollView.visibility = View.GONE
    }
    private fun showDownCounterConstraintLayout() {
        gameOptionsNestedScrollView.visibility = View.GONE
        downCounterConstraintLayout.visibility = View.VISIBLE
        roundResultNestedScrollView.visibility = View.GONE

        startCountdownTimer({ secondsRemaining: Int ->
            val numberToDisplay = secondsRemaining + 1
            downCounterTv.text = numberToDisplay.toString()
        }) {
            showRoundResultNestedScrollView()
        }

    }

    private fun startCountdownTimer(
        onTick: (Int) -> Unit,
        onFinish: () -> Unit
    ) {
        object : CountDownTimer((2 + 1) * 1000L, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = (millisUntilFinished / 1000).toInt()
                onTick(secondsRemaining)
            }

            override fun onFinish() {
                onFinish()
            }
        }.start()
    }

    private fun showRoundResultNestedScrollView(){
        gameOptionsNestedScrollView.visibility = View.GONE
        downCounterConstraintLayout.visibility = View.GONE
        roundResultNestedScrollView.visibility = View.VISIBLE

        resultTitleTv.text = "Você ganhou!"
    }
}