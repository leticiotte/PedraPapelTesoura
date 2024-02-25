package com.example.pedrapapeltesoura.infrastructure.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.pedrapapeltesoura.domain.model.enums.Choice
import com.example.pedrapapeltesoura.domain.model.enums.RoundResult
import kotlin.random.Random

class RoundService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    fun getRandomChoice(): Choice {
        val choices = Choice.values()
        return choices[Random.nextInt(choices.size)]
    }

    fun getTwoPlayersRoundResult(userChoice: Choice, appPlayerChoice: Choice): RoundResult {
        return if (userChoice == appPlayerChoice) {
            RoundResult.TIE
        } else if (
            (userChoice == Choice.ROCK && appPlayerChoice == Choice.SCISSOR)
            || (userChoice == Choice.PAPER && appPlayerChoice == Choice.ROCK)
            || (userChoice == Choice.SCISSOR && appPlayerChoice == Choice.PAPER)
        ) {
            RoundResult.WIN
        } else {
            RoundResult.DEFEAT
        }
    }

    fun getThreePlayersRoundResult(
        userChoice: Choice,
        appPlayer1Choice: Choice,
        appPlayer2Choice: Choice
    ): RoundResult {
        return if (
            (userChoice == appPlayer1Choice && userChoice == appPlayer2Choice)
            || isTieWithAppPlayer1(userChoice, appPlayer1Choice, appPlayer2Choice)
            || isTieWithAppPlayer2(userChoice, appPlayer1Choice, appPlayer2Choice)
        ) {
            RoundResult.TIE
        } else if (
            (userChoice == Choice.ROCK && appPlayer1Choice == Choice.SCISSOR && appPlayer2Choice == Choice.SCISSOR)
            || (userChoice == Choice.PAPER && appPlayer1Choice == Choice.ROCK && appPlayer2Choice == Choice.ROCK)
            || (userChoice == Choice.SCISSOR && appPlayer1Choice == Choice.PAPER && appPlayer2Choice == Choice.PAPER)
        ) {
            RoundResult.WIN
        } else {
            RoundResult.DEFEAT
        }
    }

    private fun isTieWithAppPlayer1(
        userChoice: Choice,
        appPlayer1Choice: Choice,
        appPlayer2Choice: Choice
    ): Boolean {
        return ((userChoice == Choice.ROCK && appPlayer1Choice == Choice.ROCK && appPlayer2Choice == Choice.SCISSOR)
                || (userChoice == Choice.PAPER && appPlayer1Choice == Choice.PAPER && appPlayer2Choice == Choice.ROCK)
                || (userChoice == Choice.SCISSOR && appPlayer1Choice == Choice.SCISSOR && appPlayer2Choice == Choice.PAPER))
    }

    private fun isTieWithAppPlayer2(
        userChoice: Choice,
        appPlayer1Choice: Choice,
        appPlayer2Choice: Choice
    ): Boolean {
        return ((userChoice == Choice.ROCK && appPlayer2Choice == Choice.ROCK && appPlayer1Choice == Choice.SCISSOR)
                || (userChoice == Choice.PAPER && appPlayer2Choice == Choice.PAPER && appPlayer1Choice == Choice.ROCK)
                || (userChoice == Choice.SCISSOR && appPlayer2Choice == Choice.SCISSOR && appPlayer1Choice == Choice.PAPER))
    }
}