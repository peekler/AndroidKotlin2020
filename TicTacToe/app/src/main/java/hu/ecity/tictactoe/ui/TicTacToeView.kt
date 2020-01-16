package hu.ecity.tictactoe.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import hu.ecity.tictactoe.MainActivity
import hu.ecity.tictactoe.R
import hu.ecity.tictactoe.model.TicTacToeModel

class TicTacToeView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {

    var paintBg: Paint = Paint()
    var paintLine: Paint = Paint()

    var paintText: Paint = Paint()

    var bitmapBg : Bitmap = BitmapFactory.decodeResource(context?.resources,
        R.drawable.mountain)

    init {
        paintBg.color = Color.BLACK
        paintBg.style = Paint.Style.FILL

        paintLine.color = Color.WHITE
        paintLine.style = Paint.Style.STROKE
        paintLine.strokeWidth = 5f

        paintText.color = Color.GREEN
        paintText.textSize = 80f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        bitmapBg = Bitmap.createScaledBitmap(bitmapBg, width, height, false)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawRect(
            0f, 0f,
            width.toFloat(), height.toFloat(), paintBg
        )

        canvas?.drawBitmap(bitmapBg,0f, 0f, null)

        canvas?.drawText("HELLO", 0f, 100f, paintText)

        drawBoard(canvas)

        drawPlayers(canvas)
    }

    private fun drawBoard(canvas: Canvas?) {
        // border
        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintLine)

        // two horizontal lines
        canvas?.drawLine(
            0f, (height / 3).toFloat(), width.toFloat(), (height / 3).toFloat(),
            paintLine
        )
        canvas?.drawLine(
            0f, (2 * height / 3).toFloat(), width.toFloat(),
            (2 * height / 3).toFloat(), paintLine
        )
        // two vertical lines
        canvas?.drawLine(
            (width / 3).toFloat(), 0f, (width / 3).toFloat(), height.toFloat(),
            paintLine
        )
        canvas?.drawLine(
            (2 * width / 3).toFloat(), 0f, (2 * width / 3).toFloat(), height.toFloat(),
            paintLine
        )
    }

    private fun drawPlayers(canvas: Canvas?) {
        for (i in 0..2) {
            for (j in 0..2) {
                if (TicTacToeModel.getFieldContent(i, j) == TicTacToeModel.CIRCLE) {
                    val centerX = (i * width / 3 + width / 6).toFloat()
                    val centerY = (j * height / 3 + height / 6).toFloat()
                    val radius = height / 6 - 2

                    canvas?.drawCircle(centerX, centerY, radius.toFloat(), paintLine)
                } else if (TicTacToeModel.getFieldContent(i, j) == TicTacToeModel.CROSS) {
                    canvas?.drawLine(
                        (i * width / 3).toFloat(), (j * height / 3).toFloat(),
                        ((i + 1) * width / 3).toFloat(),
                        ((j + 1) * height / 3).toFloat(), paintLine
                    )

                    canvas?.drawLine(
                        ((i + 1) * width / 3).toFloat(), (j * height / 3).toFloat(),
                        (i * width / 3).toFloat(), ((j + 1) * height / 3).toFloat(), paintLine
                    )
                }
            }
        }
    }



    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_DOWN) {

            val tX = event.x.toInt() / (width / 3)
            val tY = event.y.toInt() / (height / 3)

            if (tX < 3 && tY < 3 &&
                TicTacToeModel.getFieldContent(tX, tY) == TicTacToeModel.EMPTY) {
                TicTacToeModel.setFieldContent(tX, tY,
                    TicTacToeModel.getNextPlayer())

                TicTacToeModel.changePlayer()

                if (TicTacToeModel.getNextPlayer() == TicTacToeModel.CIRCLE) {
                    (context as MainActivity).showStatusText("Next is Circle")
                } else {
                    (context as MainActivity).showStatusText("Next is Cross")
                }

                invalidate()
            }


        }

        return true
    }

    public fun reset() {
        //tbd
        TicTacToeModel.resetModel()

        //újra rajzolás
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val w = View.MeasureSpec.getSize(widthMeasureSpec)
        val h = View.MeasureSpec.getSize(heightMeasureSpec)
        val d = if (w == 0) h else if (h == 0) w else if (w < h) w else h
        setMeasuredDimension(d, d)
    }

}