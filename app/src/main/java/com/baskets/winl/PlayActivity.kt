package com.baskets.winl

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.baskets.winl.databinding.ActivityPlayBinding

class PlayActivity : AppCompatActivity() {



//    private var fistTapToSwap = true
//    private var fistTapToSwapNumber = 0
//
//    private val b = arrayListOf(15, 1, 2, 3, 13, 7, 6, 5, 8, 9, 10, 12, 11, 4, 14, 0)
//    private val a = arrayListOf(
//        R.id.w1,
//        R.id.w2,
//        R.id.w3,
//        R.id.w4,
//        R.id.w5,
//        R.id.w6,
//        R.id.w7,
//        R.id.w8,
//        R.id.w9,
//        R.id.w10,
//        R.id.w11,
//        R.id.w12,
//        R.id.w13,
//        R.id.w14,
//        R.id.w15,
//        R.id.w16
//    )
//    private fun checkWin(): Boolean {
//
//        for ((i, value) in b.withIndex()) {
//            if (i != value) {
//                return false
//            }
//        }
//        return true
//    }
//
//    private fun checkWinWork() {
//        if (checkWin()) {
//            Toast.makeText(this, "Winline!", Toast.LENGTH_SHORT).show()
//            stop = true
//            for (id in a) {
//                val img = findViewById<ImageView>(id)
//                val param = img.layoutParams as ViewGroup.MarginLayoutParams
//                param.setMargins(0,0,0,0)
//                img.layoutParams = param
//            }
//
//        }
//    }

private val binding by viewBinding(ActivityPlayBinding::bind)

    override fun onBackPressed() {
        if (binding.game.running) {
            binding.game.stop()
            binding.pause.visibility = View.VISIBLE

        } else super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        binding.game.stop()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)


        binding.pause.setOnClickListener {
            binding.game.play()
            binding.pause.visibility = View.GONE
        }


//        findViewById<ImageView>(R.id.btnNext).setOnClickListener {
//            val i = Intent(this@PlayActivity, PlayActivity::class.java)
//            startActivity(i)
//            finish()
//        }
//        findViewById<ImageView>(R.id.btnMenu).setOnClickListener {
//            finish()
//        }


//        val im = arrayListOf(
//            R.drawable.image_part_001,
//            R.drawable.image_part_002,
//            R.drawable.image_part_003,
//            R.drawable.image_part_004,
//            R.drawable.image_part_005,
//            R.drawable.image_part_006,
//            R.drawable.image_part_007,
//            R.drawable.image_part_008,
//            R.drawable.image_part_009,
//            R.drawable.image_part_010,
//            R.drawable.image_part_011,
//            R.drawable.image_part_012,
//            R.drawable.image_part_013,
//            R.drawable.image_part_014,
//            R.drawable.image_part_015,
//            R.drawable.image_part_016
//        )

//        for ((i, id) in a.withIndex()) {
//            val img = findViewById<ImageView>(id)
//            img.setImageResource(im[b[i]])
//
//            img.setOnClickListener {
//                if (!stop) {
//                    if (fistTapToSwap) { // запоминаем какую зажимаем
//                        fistTapToSwapNumber = i
//                    } else { // обмен и сброс
//                        val imgToSwap = findViewById<ImageView>(a[fistTapToSwapNumber])
//
//                        imgToSwap.setImageResource(im[b[i]])
//                        img.setImageResource(im[b[fistTapToSwapNumber]])
//                        Collections.swap(b, i, fistTapToSwapNumber)
//
//                    }
//
//                    // проверка
//                    checkWinWork()
//
//                    fistTapToSwap = !fistTapToSwap
//                }
//            }
//        }

    }
}