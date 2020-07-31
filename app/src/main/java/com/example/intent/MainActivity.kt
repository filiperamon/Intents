package com.example.intent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private val listActions: Array<String> by lazy { resources.getStringArray(R.array.intent_actions) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listView = ListView(this)
        setContentView(listView)

        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, listActions)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            openIntentAtPosition(position)
        }
    }

    private fun openIntentAtPosition(position: Int){
        showShortToast(listActions[position])
        val uri:Uri?
        val intent:Intent?

        when (position) {
            0 -> { //Abri browser
                uri = Uri.parse(resources.getString(R.string.uri_nelson_blog))
                intent = Intent(Intent.ACTION_VIEW, uri)
                openIntent(intent)
            }
            1 ->{//Chamada

            }
            2 -> {//Mapa

            }
            3 -> {//SMS

            }
            4 -> {//Compartilhar

            }
            5 -> {//Alarme

            }
            6 -> {//Busca web

            }
            7 -> {//Settings

            }
            8 -> {//Ação 1

            }
            9 -> {//Ação 2

            }
            else -> finish()
        }
    }

    private fun openIntent(intent: Intent) {
        if(intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }else{
            showShortToast(resources.getString(R.string.error_intent))
        }
    }
}