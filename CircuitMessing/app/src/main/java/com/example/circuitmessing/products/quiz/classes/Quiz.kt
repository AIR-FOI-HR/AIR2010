package com.example.circuitmessing.products.quiz.classes

import android.util.Log
import androidx.fragment.app.Fragment
import com.example.circuitmessing.utils.preferences
import com.example.circuitmessing.products.ProgressManager
import com.example.circuitmessing.products.quiz.views.EndQuizFragment
import com.example.circuitmessing.products.quiz.views.QuizQuestionFragment
import com.example.radioquestion.MultipleChoiceQuestionFragment
import com.example.radioquestion.MultipleQuestion
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FValueEventListener(val onDataChange: (DataSnapshot) -> Unit, val onError: (DatabaseError) -> Unit) : ValueEventListener {
    override fun onDataChange(data: DataSnapshot) = onDataChange.invoke(data)
    override fun onCancelled(error: DatabaseError) = onError.invoke(error)
}

public class Quiz(var ProductName: String){
    public var Questions: MutableList<com.example.core.IQuestion> = arrayListOf()

    var index = -1
    var points = 0

    fun DisplayQuestion() : Fragment {
        index++
        if (index >= Questions.size) {
            calculatePoints()
            var title: String = "/"
            if (points > 400){
                when(ProductName){
                    "Ringo" -> title = "ringoQuizExpert"
                    "Nibble" -> title = "nibbleQuizExpert"
                    "Makerbuino" -> title = "makerbuinoQuizExpert"
                }
                ProgressManager.giveUserTitle(preferences.username, title)
            }
            var endFragment: Fragment = EndQuizFragment(points, title)
            points = 0
            index = -1
            return endFragment
        } else {
            return Questions[index].QuestionFragment
        }
    }

    private fun calculatePoints() {
        for (question in Questions){
            var wasAnsweredCorrectly = question.checkAnswers()
            if (wasAnsweredCorrectly){
                points += 150
            }
        }
        ProgressManager.giverUserPointsAfterQuiz(ProductName, points)
        QuizQuestionFragment.userAnswer.clear()
        MultipleChoiceQuestionFragment.userAnswer.clear()
    }

    suspend fun FetchQuestions() {
        val database = Firebase.database.reference

        val pageRef: DatabaseReference
        if (ProductName == "Nibble") {
            pageRef = database.child("nibbleRadioQuestions") //.child("question1")}
        }
        else if (ProductName == "Ringo") {
            pageRef = database.child("ringoRadioQuestions")
        }
        else {
            pageRef = database.child("makerbuinoRadioQuestions")
        }
        val dataSnapshot = pageRef.getSnapshotValue()

        var list: MutableList<com.example.core.IQuestion> = ArrayList()

        for (ds in dataSnapshot.children) {
            Log.d("TAG0", ds.toString())
            var text: String = ""
            val answers: MutableList<String> = ArrayList()
            var correct: String = ""

            for (child in ds.children) {
                /* GET QUESTION TEXT */
                if (child.key.toString() == "text"){
                    text = child.value.toString()
                }

                /* GET ANSWERS */
                if (child.key.toString() == "answers"){
                    for (answer in child.children){
                        answers.add(answer.value.toString())
                    }
                }

                /* GET CORRECT ANSWER */
                if (child.key.toString() == "correct"){
                    correct = child.value.toString()
                }
            }

            CreateQuestion(text, answers, listOf(correct))
        }
    }

    suspend fun DatabaseReference.getSnapshotValue(): DataSnapshot {
        return withContext(Dispatchers.IO) {
            suspendCoroutine<DataSnapshot> { continuation ->
                addListenerForSingleValueEvent(FValueEventListener(
                    onDataChange = { continuation.resume(it) },
                    onError = { continuation.resumeWithException(it.toException()) }
                ))
            }
        }
    }

    private fun CreateQuestion(text: String, answers: List<String>, correctAnswers: List<String>) {
        //val question = RadioQuestion(text, answers, correctAnswers)
        val question = MultipleQuestion(text, answers, correctAnswers)
        Questions.add(question)
    }
}
