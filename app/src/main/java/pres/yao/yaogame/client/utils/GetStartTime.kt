package pres.yao.yaogame.client.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object GetStartTime {
    fun getTodayDate(): String{
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatted = current.format(formatter)
        return formatted.toString()
    }

    @SuppressLint("SimpleDateFormat")
    fun getTodayNextDate(): String{
        val date = SimpleDateFormat("yyyy-MM-dd").parse(getTodayDate())
        val cal = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.DAY_OF_YEAR,1)
        val nextdate = cal.time
        return SimpleDateFormat("yyyy-MM-dd").format(nextdate)
    }

    @SuppressLint("SimpleDateFormat")
    fun getTodayPreDate(): String{
        val date = SimpleDateFormat("yyyy-MM-dd").parse(getTodayDate())
        val cal = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.DAY_OF_YEAR,-1)
        val predate = cal.time
        return SimpleDateFormat("yyyy-MM-dd").format(predate)
    }
}