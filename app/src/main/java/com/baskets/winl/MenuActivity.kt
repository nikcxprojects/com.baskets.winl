package com.baskets.winl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        findViewById<LinearLayout>(R.id.start).setOnClickListener {
            val i = Intent(this@MenuActivity, PlayActivity::class.java)
            startActivity(i)
        }
        findViewById<LinearLayout>(R.id.wt).setOnClickListener {
            val i = Intent(this@MenuActivity, HowToActivity::class.java)
            startActivity(i)
        }
        findViewById<LinearLayout>(R.id.br).setOnClickListener {
            val i = Intent(this@MenuActivity, BestResultsActivity::class.java)
            startActivity(i)
        }

    }
}