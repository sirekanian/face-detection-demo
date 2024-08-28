package org.sirekanyan.facedetection.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Style
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class FaceBoxesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {

    private val boxes = mutableListOf<Rect>()
    private val paint = Paint()

    init {
        // todo: move box style to attributes
        paint.color = Color.GREEN
        paint.style = Style.STROKE
        paint.strokeWidth = 3f
    }

    fun setBoxes(boxes: List<Rect>) {
        this.boxes.clear()
        this.boxes.addAll(boxes)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        boxes.forEach { box ->
            canvas.drawRect(box, paint)
        }
    }
}
