package com.future.movie

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class InputActivity : AppCompatActivity() {

    companion object {

        @JvmStatic
        fun launch(activity: Activity) {
            val intent = Intent(activity, InputActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val frag = InputFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, frag, "InputFragment")
                .commit()
        }
    }
}