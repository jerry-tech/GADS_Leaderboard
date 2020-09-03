package com.google.gadsleaderboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_submit_form.*

class SubmitForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit_form)

        //onclick listener for back button
        btnBack.setOnClickListener { view ->
            //calling the function that cotains the intent
            goBack()
        }
    }

    private fun goBack() {
        val activityIntent = Intent(this, LeaderBoard::class.java)
        startActivity(activityIntent)
    }
}