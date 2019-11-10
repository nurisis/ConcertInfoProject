package com.secondhands.concertinfoproject.data.local

import androidx.paging.DataSource
import com.secondhands.concertinfoproject.entity.ConcertItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ConcertLocalDataSource(
    private val concertDao: ConcertDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    fun getAllBookMarks() : DataSource.Factory<Int,ConcertItem> {
        return concertDao.getAllBookMarks()
    }

    suspend fun getBookMark(title:String) : ConcertItem = withContext(ioDispatcher) {
        return@withContext concertDao.getBookMark(title)
    }

    suspend fun saveBookMark(item: ConcertItem) = withContext(ioDispatcher){
        concertDao.saveBookMark(item)
    }

    suspend fun deleteBookMark(item: ConcertItem) = withContext(ioDispatcher){
        concertDao.deleteBookMark(item)
    }
}