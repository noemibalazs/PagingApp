package com.example.pagingapp.util

import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Shader
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.example.pagingapp.R

class GradientColor : AppCompatTextView {

    constructor(context: Context) : super(context){
        enableGradient()
    }

    constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet){
        enableGradient()
    }

    constructor(context: Context, attributeSet: AttributeSet, defInt: Int): super(context, attributeSet, defInt){
        enableGradient()
    }

    private fun enableGradient(){

        val textWidth = paint.measureText(this.text.toString())
        val textHeight = lineHeight.toFloat()

        val start = ContextCompat.getColor(context, R.color.gradient_start)
        val center = ContextCompat.getColor(context, R.color.gradient_center)
        val end = ContextCompat.getColor(context, R.color.colorPrimary)

        val shader: Shader = LinearGradient(
            0f,
            0f,
            textWidth,
            textHeight,
            intArrayOf(start, center, end),
            null,
            Shader.TileMode.CLAMP
        )

        this.paint.shader = shader

        invalidate()
    }
}