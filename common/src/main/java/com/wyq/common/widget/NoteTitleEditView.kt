package com.wyq.common.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.blankj.utilcode.util.SizeUtils
import timber.log.Timber

class NoteTitleEditView : AppCompatEditText {

    private var paint: Paint = Paint()
    private var rect = Rect()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Timber.d("onMeasure.height: $height")
    }


    init {
        paint.style = Paint.Style.STROKE
        paint.color = Color.BLACK
        paint.strokeWidth = SizeUtils.dp2px(1f).toFloat()
        addTextChangedListener(InnerTextChanged())
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (line in 0 until lineCount) {
            rect.setEmpty()
            getLineBounds(line, rect)
            val y = rect.bottom - SizeUtils.dp2px(1f).toFloat()
            val lineWidth = layout?.getLineWidth(line) ?: (this.width).toFloat()
            canvas.drawLine(
                0F,
                y,
                lineWidth, y, paint
            )
        }
    }

    inner class InnerTextChanged : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            Timber.d("afterTextChanged ${s.toString()}")
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            Timber.d("beforeTextChanged ${s.toString()}")
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            Timber.d("onTextChanged ${s.toString()}")

            Timber.d("onTextChanged.lineCount: $lineCount")
            if (lineCount > 3) {
                text?.delete(selectionEnd - 1, selectionStart)
            }
        }
    }
}

