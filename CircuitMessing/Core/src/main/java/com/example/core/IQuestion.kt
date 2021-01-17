package com.example.core

import androidx.fragment.app.Fragment

public interface IQuestion {
    public var QuestionText: String
    public var Answers: List<String>
    public var CorrectAnswers: List<String>
    public var QuestionFragment: Fragment

    public fun checkAnswers(): Boolean
}