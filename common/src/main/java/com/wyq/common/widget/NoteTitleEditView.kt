package com.wyq.common.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.text.*
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import com.blankj.utilcode.util.SizeUtils
import timber.log.Timber

class NoteTitleEditView : AppCompatEditText {

    private var paint: Paint = Paint()
    private var rect = Rect()
    private var noteTileEditListener: NoteTileEditListener? = null

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


        filters = arrayOf(EnterNewLineInputFilter());
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

        override fun onTextChanged(string: CharSequence, start: Int, before: Int, count: Int) {
            if (lineCount > 3) {
                text?.delete(selectionEnd - 1, selectionStart)
            }
        }
    }

    interface NoteTileEditListener {
        fun onTextChanged(newLine: Boolean)
    }

    fun setNoteTileEditListener(listener: NoteTileEditListener) {
        this.noteTileEditListener = listener
    }

    inner class EnterNewLineInputFilter : InputFilter {
        override fun filter(
            string: CharSequence,
            start: Int,
            end: Int,
            dest: Spanned?,
            dstart: Int,
            dend: Int
        ): CharSequence? {
            val newLine = string.isNotEmpty() && string[string.length - 1] == '\n'
            noteTileEditListener?.onTextChanged(newLine)
            if (newLine) {
                return ""
            }
            return null
        }

    }
}

