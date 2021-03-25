package com.joesamyn.envelope.repositories

import androidx.room.TypeConverter
import java.util.*

/**
 * Converts Date to Unix Timestamp and Unix Timestamp to Date
 */
class DateConverter {

    /**
     * Convert a unix timestamp to a Java Date object
     */
    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return value.let { Date(it)}
    }

    /**
     * Convert a Java Date object to a Unix Timestamp
     */
    @TypeConverter
    fun toTimestamp(value: Date): Long {
        return value.time.toLong()
    }

}