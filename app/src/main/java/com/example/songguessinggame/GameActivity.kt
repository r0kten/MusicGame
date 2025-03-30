package com.example.songguessinggame

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private lateinit var timerText: TextView
    private lateinit var scoreText: TextView
    private lateinit var songHint: TextView
    private lateinit var buttonJuiceWrld: Button
    private lateinit var buttonXXXTentacion: Button
    private lateinit var buttonKendrick: Button

    private var score = 0
    private var currentSong: Pair<String, String>? = null

    private val songs = listOf(
        Pair("Lucid Dreams", "Juice WRLD"),
        Pair("SAD!", "XXXTentacion"),
        Pair("HUMBLE.", "Kendrick Lamar"),
        Pair("All Girls Are the Same", "Juice WRLD"),
        Pair("Moonlight", "XXXTentacion"),
        Pair("DNA.", "Kendrick Lamar")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        timerText = findViewById(R.id.timerText)
        scoreText = findViewById(R.id.scoreText)
        songHint = findViewById(R.id.songHint)
        buttonJuiceWrld = findViewById(R.id.buttonJuiceWrld)
        buttonXXXTentacion = findViewById(R.id.buttonXXXTentacion)
        buttonKendrick = findViewById(R.id.buttonKendrick)

        startGame()
    }

    private fun startGame() {
        score = 0
        scoreText.text = "Score: $score"
        startTimer()
        nextSong()

        buttonJuiceWrld.setOnClickListener { checkAnswer("Juice WRLD") }
        buttonXXXTentacion.setOnClickListener { checkAnswer("XXXTentacion") }
        buttonKendrick.setOnClickListener { checkAnswer("Kendrick Lamar") }
    }

    private fun startTimer() {
        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerText.text = getString(R.string.timer_text, millisUntilFinished / 1000)
            }

            override fun onFinish() {
                timerText.text = getString(R.string.time_up)
                songHint.text = getString(R.string.game_over, score)
                disableButtons()
            }
        }.start()
    }

    private fun nextSong() {
        currentSong = songs[Random.nextInt(songs.size)]
        songHint.text = "Song: ${currentSong?.first}" // Можна додати як ресурс, якщо потрібно
    }

    private fun checkAnswer(selectedArtist: String) {
        if (currentSong?.second == selectedArtist) {
            score++
            scoreText.text = getString(R.string.score_text, score)
        }
        nextSong()
    }

    private fun disableButtons() {
        buttonJuiceWrld.isEnabled = false
        buttonXXXTentacion.isEnabled = false
        buttonKendrick.isEnabled = false
    }
}