package com.example.intent

import android.app.SearchManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.Settings
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val listActions: Array<String> by lazy { resources.getStringArray(R.array.intent_actions) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listView = ListView(this)
        setContentView(listView)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listActions)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            openIntentAtPosition(position)
        }
    }

    private fun callNumber() {
        val uri = Uri.parse("tel:999887766")
        intent = Intent(Intent.ACTION_DIAL, uri)
        openIntent(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.first() == PackageManager.PERMISSION_GRANTED) {
            callNumber()
        }
    }

    private fun openIntentAtPosition(position: Int) {
        showShortToast(listActions[position])
        val uri: Uri?
        val intent: Intent?

        when (position) {
            0 -> { //Abri browser
                uri = Uri.parse(resources.getString(R.string.uri_nelson_blog))
                intent = Intent(Intent.ACTION_VIEW, uri)
                openIntent(intent)
            }
            1 -> {//Chamada
                if (ActivityCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.CALL_PHONE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.CALL_PHONE), 0
                    )
                } else {
                    callNumber()
                }
            }
            2 -> {//Mapa
                uri = Uri.parse("geo:o,o?q=RUA+Almeida,Recife")
                intent = Intent(Intent.ACTION_VIEW, uri)
                openIntent(intent)
            }
            3 -> {//SMS
                uri = Uri.parse("sms:12345")
                intent = Intent(Intent.ACTION_VIEW, uri)
                    .putExtra("sms_body", "Corpo SMS")
                openIntent(intent)
            }
            4 -> {//Compartilhar
                intent = Intent()
                    .setAction(Intent.ACTION_SEND)
                    .putExtra(Intent.EXTRA_INTENT, "Compartilhar via Intent")
                    .setType("text/plain")
                openIntent(intent)
            }
            5 -> {//Alarme
                intent = Intent(AlarmClock.ACTION_SET_ALARM)
                    .putExtra(AlarmClock.EXTRA_MESSAGE, "Estudar Kotlin")
                    .putExtra(AlarmClock.EXTRA_HOUR, 12)
                    .putExtra(AlarmClock.EXTRA_MINUTES, 0)
                    .putExtra(AlarmClock.EXTRA_SKIP_UI, true)
                    .putExtra(
                        AlarmClock.EXTRA_DAYS, arrayListOf(
                            Calendar.MONDAY,
                            Calendar.WEDNESDAY,
                            Calendar.FRIDAY
                        )
                    )
                openIntent(intent)
            }
            6 -> {//Busca web
                intent = Intent(Intent.ACTION_SEARCH)
                    .putExtra(SearchManager.QUERY, "Novatec")
                openIntent(intent)
            }
            7 -> {//Settings
                intent = Intent(Settings.ACTION_SETTINGS)
                openIntent(intent)
            }
            8 -> {//Ação 1
                intent = Intent("dominando.android.CUSTOM_ACTION")
                openIntent(intent)
            }
            9 -> {//Ação 2
                uri = Uri.parse("produto://Notbook/Slim")
                intent = Intent(Intent.ACTION_VIEW, uri)
                openIntent(intent)
            }
            else -> finish()
        }
    }

    private fun openIntent(intent: Intent) {
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            showShortToast(resources.getString(R.string.error_intent))
        }
    }
}