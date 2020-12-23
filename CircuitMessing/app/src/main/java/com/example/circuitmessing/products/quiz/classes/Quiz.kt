package com.example.circuitmessing.products.quiz.classes

import android.util.Log
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

public class Quiz( public var ProductName: String ){
    public var Questions: MutableList<com.example.core.IQuestion> = arrayListOf()

    public fun DisplayQuestion() {
        // IMPLEMENT LOGIC FOR DISPLAYING QUESTION ATRIBUTES
        // List of questions is in MutableList Questions above
        TODO()
    }

    public suspend fun FetchQuestions() {
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
            var answers: MutableList<String> = ArrayList()
            var correct: String = ""

            for (child in ds.children) {
                /* GET QUESTION TEXT */
                if (child.key.toString() == "text"){
                    Log.d("TAG1", child.value.toString())
                    text = child.value.toString()
                }

                /* GET ANSWERS */
                if (child.key.toString() == "answers"){
                    Log.d("TAG2", child.value.toString())
                    for (answer in child.children){
                        answers.add(answer.value.toString())
                    }
                }

                /* GET CORRECT ANSWER */
                if (child.key.toString() == "correct"){
                    Log.d("TAG3", child.value.toString())
                    correct = child.value.toString()
                }
            }

            /*
            val question = object: IQuestion{
                override var QuestionText: String = text
                override var Answers: List<String> = answers
                override var CorrectAnswer: String = correct
                override var WasAnsweredCorrectly: Boolean = false
                override fun CheckAnswers(answer: String): Boolean {
                    return answer == CorrectAnswer
                }
            }
            list.add(question)
             */
            CreateQuestion(text, answers, listOf(correct))
        }
        //Questions = list
        //Log.d("TAG QUESTIONS", Questions.toString())
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
        val question = RadioQuestion(text, answers, false, correctAnswers)
        //val question = MultipleQuestion(text, answers, false, correctAnswers)
        Log.d("TAG NEW QUESTION", question.CorrectAnswers[0])
        Questions.add(question)
    }
}
