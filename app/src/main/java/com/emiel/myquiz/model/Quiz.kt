package com.emiel.myquiz.model

import org.jsoup.Jsoup
import java.net.URL

class Quiz (var results : List<Questions> = emptyList()){

    fun readJson(url: String) : String {
        return (URL(url).readText())
    }


}

data class Questions(
    var question: String,
    var correct_answer: String,
    var incorrect_answers: List<String>
)