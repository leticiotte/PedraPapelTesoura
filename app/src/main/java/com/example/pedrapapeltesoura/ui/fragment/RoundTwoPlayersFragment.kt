package com.example.pedrapapeltesoura.ui.fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pedrapapeltesoura.R
import com.example.pedrapapeltesoura.databinding.FragmentTwoPlayersRoundBinding
import com.example.pedrapapeltesoura.domain.model.enums.Choice
import com.example.pedrapapeltesoura.domain.model.enums.RoundResult
import com.example.pedrapapeltesoura.domain.model.interfaces.ToolbarConfig
import com.example.pedrapapeltesoura.infrastructure.service.RoundService
import com.google.android.material.appbar.MaterialToolbar

class RoundTwoPlayersFragment : Fragment(), ToolbarConfig {
    private var _binding: FragmentTwoPlayersRoundBinding? = null
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
    private lateinit var userChoice2PImg: ImageView
    private lateinit var appPlayerChoice2PImg: ImageView
    private lateinit var playAgain2PBtn: Button


    private val roundService: RoundService = RoundService()

    private lateinit var userChoice: Choice
    private lateinit var appPlayerChoice: Choice

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTwoPlayersRoundBinding.inflate(inflater, container, false)

        setupTextViews()
        setupButtons()
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
            findNavController().navigate(R.id.action_RoundTwoPlayersFragment_to_StartGameFragment)
        }
    }

    private fun setupLayouts() {
        gameOptionsNestedScrollView = binding.gameOptions2PNestedScrollView
        downCounterConstraintLayout = binding.downCounter2PConstraintLayout
        roundResultNestedScrollView = binding.roundResult2PNestedScrollView
    }

    private fun setupTextViews() {
        downCounterTv = binding.downCounter2PTv
        resultTitleTv = binding.resultTitle2PTv
    }

    private fun setupButtons() {
        playAgain2PBtn = binding.playAgain2PBtn

        playAgain2PBtn.setOnClickListener {
            showGameOptionsNestedScrollView()
        }
    }

    private fun setupImageViews() {
        rockImg = binding.rock2PImg
        paperImg = binding.paper2PImg
        scissorImg = binding.scissor2PImg
        userChoice2PImg = binding.userChoice2PImg
        appPlayerChoice2PImg = binding.appPlayerChoice2PImg

        rockImg.setOnClickListener {
            userChoice = Choice.ROCK
            processRoundResult()
            showDownCounterConstraintLayout()
        }

        paperImg.setOnClickListener {
            userChoice = Choice.PAPER
            processRoundResult()
            showDownCounterConstraintLayout()
        }

        scissorImg.setOnClickListener {
            userChoice = Choice.SCISSOR
            processRoundResult()
            showDownCounterConstraintLayout()
        }
    }

    private fun showGameOptionsNestedScrollView() {
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

    private fun showRoundResultNestedScrollView() {
        gameOptionsNestedScrollView.visibility = View.GONE
        downCounterConstraintLayout.visibility = View.GONE
        roundResultNestedScrollView.visibility = View.VISIBLE
    }

    private fun processRoundResult(){
        appPlayerChoice = roundService.getRandomChoice()

        val roundResult: RoundResult = roundService.getTwoPlayersRoundResult(userChoice, appPlayerChoice)
        setRoundTitleTvText(roundResult)
        setRoundResultImages()

        showDownCounterConstraintLayout()
    }

    private fun setRoundTitleTvText(roundResult: RoundResult) {
        when (roundResult) {
            RoundResult.WIN -> resultTitleTv.text = getString(R.string.user_won_tv)
            RoundResult.DEFEAT -> resultTitleTv.text = getString(R.string.user_lost_tv)
            else -> resultTitleTv.text = getString(R.string.tied_tv)
        }
    }

    private fun setRoundResultImages() {
        userChoice2PImg.setImageResource(getImageResourceByChoice(userChoice))
        appPlayerChoice2PImg.setImageResource(getImageResourceByChoice(appPlayerChoice))

    }

    private fun getImageResourceByChoice(choice: Choice): Int {
        return when (choice) {
            Choice.ROCK -> R.drawable.rock
            Choice.PAPER -> R.drawable.paper
            else -> R.drawable.scissor
        }
    }
}