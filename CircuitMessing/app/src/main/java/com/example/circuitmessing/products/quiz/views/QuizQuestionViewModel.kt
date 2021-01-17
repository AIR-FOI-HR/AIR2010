package com.example.circuitmessing.products.quiz.views

import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizQuestionViewModel : ViewModel() {
    private val answer = MutableLiveData<String>()
    val selectedAnswer: LiveData<String> get() = answer

    fun selectAnswer(text: String) {
        answer.value = text
    }
}