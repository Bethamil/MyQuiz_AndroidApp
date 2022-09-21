package com.emiel.myquiz.model

import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.gson.Gson
import org.jsoup.Jsoup

fun main() {
    println("Hello")
    val game = Game()


    println(game.currentQuestion)
    println(game.allAnswers)
    game.nextQuestion("")

    println(game.currentQuestion)
    println(game.allAnswers)
    game.nextQuestion("")

    println(game.currentQuestion)
    println(game.allAnswers)
    game.nextQuestion("")

    println(game.currentQuestion)
    println(game.allAnswers)
    game.nextQuestion("")


}

class Game(
    var quiz: Quiz = Quiz()
) {
    var currentQuestionNumber = 0
    var score = 0
    var currentQuestion: Questions? = null
    var allAnswers: MutableList<String> = mutableListOf()

    init {
        fillQuiz()
        currentQuestion = quiz.results[currentQuestionNumber]
        fillAllAnswersList()

    }

    private fun fillQuiz() {
        val json = quiz.readJson("https://opentdb.com/api.php?amount=10&category=18&type=multiple")
        val gson = Gson()
        quiz = gson.fromJson(json, Quiz::class.java)

    }

    fun nextQuestion(yourAnswer: String) {
        checkAnswer(yourAnswer)
        currentQuestion = quiz.results[++currentQuestionNumber]
        fillAllAnswersList()


    }

    fun checkAnswer(yourAnswer: String) {
        val correctAnswer = Jsoup.parse(currentQuestion?.correct_answer).text()
        if (yourAnswer == correctAnswer) {
            score++
        }
    }

    private fun fillAllAnswersList() {
        allAnswers = mutableListOf(Jsoup.parse(currentQuestion!!.correct_answer).text())
        for (answer in currentQuestion!!.incorrect_answers) {
            allAnswers.add(Jsoup.parse(answer).text())
        }
        allAnswers.shuffle()
    }

}