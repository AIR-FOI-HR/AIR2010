package com.example.core

import androidx.fragment.app.Fragment

public interface IQuestion {
    public var QuestionFragment: Fragment

    public fun checkAnswers(): Boolean

}