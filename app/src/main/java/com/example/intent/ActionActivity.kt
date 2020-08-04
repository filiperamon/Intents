package com.example.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_action.*

class ActionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action)

        if(intent.action == Intent.ACTION_VIEW){
            val uri = intent.data
            val resultString = uri.toString() +" - "+ uri?.host + " - " + uri?.path
            text_action.text = getString(R.string.custom_action2) + " - " + resultString
        } else if (intent.action == "dominando.android.CUSTOM_ACTION"){
            text_action.text = resources.getString(R.string.custom_action1)
        }
    }
}