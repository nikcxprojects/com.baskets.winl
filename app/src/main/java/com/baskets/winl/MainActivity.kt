package com.baskets.winl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast

//bjgjifd545jiu6jgfnlkfj

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ImageView>(R.id.pol).setOnClickListener {
            val i = Intent(this@MainActivity, PrivacyPolicy::class.java)
            startActivity(i)
        }

        findViewById<LinearLayout>(R.id.btnStart).setOnClickListener {
            val i = Intent(this@MainActivity, MenuActivity::class.java)
            startActivity(i)
            finish()
        }
        findViewById<LinearLayout>(R.id.btnClose).setOnClickListener {
            finish()
        }

    }
}