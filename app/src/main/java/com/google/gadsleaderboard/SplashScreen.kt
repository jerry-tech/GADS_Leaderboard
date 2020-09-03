package com.google.gadsleaderboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {

    // This is the loading time of the splash screen
    private val TIME_OUT:Long = 9000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //intent for moving away from the splashscreen
        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity
            startActivity(Intent(this,LeaderBoard::class.java))

            // close this activity
            finish()
        }, TIME_OUT)
    }

}