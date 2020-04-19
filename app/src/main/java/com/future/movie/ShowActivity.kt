package com.future.movie

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ShowActivity : AppCompatActivity() {

    companion object {

        @JvmStatic
        fun launch(activity: Activity) {
            val intent = Intent(activity, ShowActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {

        }
    }
}