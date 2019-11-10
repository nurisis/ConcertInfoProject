package com.secondhands.navigationexamproject.data.local

import androidx.paging.DataSource
import androidx.room.*
import com.secondhands.navigationexamproject.entity.ConcertItem

@Dao
interface ConcertDao {

    @Query("SELECT * FROM concert_bookmark ORDER BY title")
    fun getAllBookMarks() : DataSource.Factory<Int,ConcertItem>

    @Query("SELECT * FROM concert_bookmark WHERE title=:title")
    suspend fun getBookMark(title:String) : ConcertItem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBookMark(item: ConcertItem)

    @Delete
    suspend fun deleteBookMark(item: ConcertItem)
}