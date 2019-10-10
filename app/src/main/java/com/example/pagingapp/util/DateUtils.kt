package com.example.pagingapp.util

import java.lang.Exception
import java.text.SimpleDateFormat

class DateUtils {

    private val simpleFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")
    private val error = "zulu"

    fun getDate(publishedAt:String): String{
        try {
            val date = simpleFormat.parse(publishedAt)
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            return sdf.format(date)

        }catch (e: Exception){
            e.printStackTrace()
            return error
        }
    }

    fun getTime(publishedAt: String): String{
        try {
            val time = simpleFormat.parse(publishedAt)
            val sdf = SimpleDateFormat("hh:mm:ss")
            return sdf.format(time)
        }catch (e:Exception){
            e.printStackTrace()
            return error
        }
    }
}