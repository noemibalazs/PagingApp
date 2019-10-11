package com.example.pagingapp.util

import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Shader
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.example.pagingapp.R

class GradientColor : AppCompatTextView {

    constructor(context: Context) : super(context) {
        enableGradient()

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        enableGradient()

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        enableGradient()

    }

    fun enableGradient() {

        val textWidth = paint.measureText(this.text.toString())
        val lineHeight = this.lineHeight.toFloat()

        val startColor = ContextCompat.getColor(context, R.color.gradient_start)
        val centerColor = ContextCompat.getColor(context, R.color.gradient_center)
        val endColor = ContextCompat.getColor(context, R.color.gradient_end)

        val shader: Shader = LinearGradient(
            0f,
            0f,
            textWidth,
            lineHeight,
            intArrayOf(startColor, centerColor, endColor),
            null,
            Shader.TileMode.CLAMP
        )

        this.paint.shader = shader

        invalidate()
    }
}