package com.baskets.winl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class BestResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_best_results)

        val c = getSharedPreferences(packageName, MODE_PRIVATE).getString("tik", "0")
        findViewById<TextView>(R.id.kslg).text = "1. $c POINTS"

        findViewById<ImageView>(R.id.back).setOnClickListener {
            finish()
        }
    }
}