package hu.ecity.tictactoe.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class TicTacToeView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {

    var paintBg : Paint = Paint()

    var paintCircle : Paint = Paint()

    //var circlePos: PointF? = null

    var circlesList = mutableListOf<PointF>()

    init {
        paintBg.color = Color.BLACK
        paintBg.style = Paint.Style.FILL

        paintCircle.color = Color.WHITE
        paintCircle.style = Paint.Style.STROKE
        paintCircle.strokeWidth = 5f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(0f, 0f,
            width.toFloat(), height.toFloat(), paintBg)

        /*if (circlePos != null) {
            canvas?.drawCircle(
                circlePos!!.x, circlePos!!.y,
                60f, paintCircle
            )
        }*/

        for (circle in circlesList) {
            canvas?.drawCircle(circle.x,
                circle.y, 50f, paintCircle)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_DOWN) {
            circlesList.add(PointF(event.x, event.y))

            invalidate()
        }

        return true
    }

    public fun reset() {
        //tömb törlés
        circlesList.clear()
        //újra rajzolás
        invalidate()
    }

}