package com.example.gymapptest.comparators

import com.example.gymapptest.data.api.model.Lesson
import java.text.SimpleDateFormat
import java.util.*
import kotlin.Comparator

class CustomComparator : Comparator<Lesson> {
    override fun compare(x: Lesson, y: Lesson): Int {
        if (getData(x).compareTo(getData(y))==0){
            if (getStartTime(x).compareTo(getStartTime(y))==0){
                return getEndTime(x).compareTo(getEndTime(y))
            }
            return getStartTime(x).compareTo(getStartTime(y))
        }
        return getData(x).compareTo(getData(y))
    }
    fun getData(lesson: Lesson): Calendar {
        val simpledateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(lesson.date)
        val calendar = GregorianCalendar()
        calendar.time = simpledateFormat
        return calendar
    }
    fun getStartTime(lesson: Lesson): Calendar {
        val simpledateFormat = SimpleDateFormat("HH:mm", Locale.ENGLISH).parse(lesson.startTime)
        val calendar = GregorianCalendar()
        calendar.time = simpledateFormat
        return calendar
    }
    fun getEndTime(lesson: Lesson): Calendar {
        val simpledateFormat = SimpleDateFormat("HH:mm", Locale.ENGLISH).parse(lesson.endTime)
        val calendar = GregorianCalendar()
        calendar.time = simpledateFormat
        return calendar
    }
}
