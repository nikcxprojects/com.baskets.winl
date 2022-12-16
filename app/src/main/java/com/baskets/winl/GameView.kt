package com.baskets.winl

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.graphics.*
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.addListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.baskets.winl.databinding.GameViewBinding
import kotlinx.coroutines.*


@SuppressLint("ClickableViewAccessibility")
class GameView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    var running = false

    private var score = 0
    private var level = 1
    private var duration: Long = 2000

    private val gateLocation by lazy {
        IntArray(4).apply {
            binding.gate.getLocationOnScreen(this)
            this[2] = this[0] + binding.gate.width
            this[3] = this[1] + binding.gate.height
        }
    }
    private val catchBorderLocation by lazy {
        IntArray(2).apply {
            binding.catchBorders.getLocationOnScreen(this)
        }
    }
    private val ballLocation
        get() = run {
            IntArray(2).apply {
                binding.ball.getLocationOnScreen(this)
                this[0] += binding.ball.width
                this[1] += binding.ball.height
            }
        }
    private val ballCentreLocation
        get() = run {
            IntArray(2).apply {
                binding.ball.getLocationOnScreen(this)
                this[0] += binding.ball.width / 2
                this[1] += binding.ball.height / 2
            }
        }

    private val binding by viewBinding(GameViewBinding::bind)
    private val y by lazy {
        ObjectAnimator().apply {
            target = binding.ball
            addListener(
                onEnd = {
                    if (ballCentreLocation[1] > gateLocation[1]) {
                        goal()
                    }
                    updateScore()
                    restart()
                }
            )
        }


    }

    private fun goal() {
        if (score > 0) score--
        if (score > 0 && score % 3 == 0) {
            level--
            duration += 200
        }
    }

    private fun save() {

        if (level >  10) {
            binding.ball1.setImageResource(R.drawable.ball1)

        } else {
            binding.ball1.setImageResource(R.drawable.ball2)

        }

        score++
        if (score % 3 == 0) {
            level++
            if(duration > 800)
            duration -= 200

        }
    }

    private val x by lazy { ObjectAnimator().apply { target = binding.ball } }


    private val gateWidth: Int
        get() = binding.gate.width

    private val paint = Paint().apply {
        this.strokeWidth = 20f
    }


    private val rand: Float
        get() = run {
            val q = gateWidth / 2 - 50
            (-q..q).random().toFloat()
        }


//    private val texture by lazy {
//        BitmapFactory.decodeResource(
//            resources,
//            R.drawable.field_texture,
//        )
//    }
//
//    private val textureDrawable by lazy {
//        ContextCompat.getDrawable(
//            context,
//            R.drawable.field_texture
//        )!!
//    }

    var sec = 59
    var min = 0

    var stop = false
    private fun timerGame(context: Context) {
        if(stop) {

            findViewById<TextView>(R.id.btnStart).text = "00 : 00"
            return
        }


        sec -= 1
        if (sec == -1) {
            sec = 59
            min -= 1
            if (min == -1) {
                Toast.makeText(context, "Time's up!", Toast.LENGTH_SHORT).show()
                stop = true
                context.getSharedPreferences(context.packageName, MODE_PRIVATE).edit().putString("tik", "$tik").apply()
                stop()
//                binding.game.stop()
//                binding.pause.visibility = View.VISIBLE
//                finish()
            }
        }

        Handler().postDelayed({
            findViewById<TextView>(R.id.btnStart).text = "$min : $sec"
            timerGame(context)
        }, 1000L)
    }

    var tik = 0
    init {
        inflate(context, R.layout.game_view, this)
        setWillNotDraw(false)
        updateScore()


        timerGame(context)

        binding.ball.setOnTouchListener { v, event ->
            v.performClick()


//            if (ballCentreLocation[1] < gateLocation[1]
//                && ballCentreLocation[1] > catchBorderLocation[1]
//            ) {
//                Log.d(TAG, "ball click ball catch")
                binding.ball.animation?.cancel()
                y.cancel()
                x.cancel()
                binding.ball.translationX = 0f
                binding.ball.translationY = 0f

                if (tik % 2 == 0) {
                    binding.ball1.setImageResource(R.drawable.ball1)

                } else {
                    binding.ball1.setImageResource(R.drawable.ball2)

                }
                findViewById<TextView>(R.id.btnCount).text = "$tik PTS"
                tik ++
                save()
                updateScore()
//            }

            true
        }
    }

    fun play(){
        if (running) return
        running = true
        start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPoint(gateLocation[0].toFloat(), gateLocation[1].toFloat(), paint)
        canvas.drawPoint(gateLocation[2].toFloat(), gateLocation[1].toFloat(), paint)
        canvas.drawPoint(gateLocation[0].toFloat(), gateLocation[3].toFloat(), paint)
        canvas.drawPoint(gateLocation[2].toFloat(), gateLocation[3].toFloat(), paint)


//        paint.style = Paint.Style.STROKE
//        paint.color = Color.BLACK
//        canvas.drawRect(100f, height - 400f, gateWidth.toFloat(), height - 100f, paint)


//        for (h in 0..hCount) {
//
//            for (w in 1..wCount) {
//                rect.set(s*w, s * h, s*(w+1), s * (h+1))
//                textureDrawable.bounds = rect
//                textureDrawable.draw(canvas)
//            }
//
//        }
    }

    //
//    private fun getGoalAnim(): ObjectAnimator {
//
//    }

    companion object {
        private const val TAG = "GameView"
    }


    private fun kick(duration: Long) {


        val animatorSet = AnimatorSet()
        animatorSet.duration = duration

        x.setValues(
            PropertyValuesHolder.ofFloat(
                View.TRANSLATION_X,
                rand
            )
        )

        y.setValues(
            PropertyValuesHolder.ofFloat(
                View.TRANSLATION_Y,
                gateLocation[3].toFloat() - ballLocation[1]
            )
        )
        animatorSet.playTogether(x, y)

        animatorSet.start()
    }


    private fun start() {

        viewScope.launch {

            delay(1000)
            binding.ball.translationX = 0f
            binding.ball.translationY = 0f
            delay(1000)
            if (!running) return@launch
            kick(duration)
        }
    }

    private fun restart() {

        viewScope.launch {
            if (!running) return@launch
            start()
        }
    }


    private fun updateScore() {
//        binding.tvScore.text = context.getString(R.string.ui_caught_title, score)
//        binding.tvLevel.text = context.getString(R.string.ui_level_title, level)
    }

    fun stop() {
        running = false
        y.cancel()
        x.cancel()
        binding.ball.translationX = 0f
        binding.ball.translationY = 0f
    }

}