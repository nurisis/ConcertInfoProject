package com.nurisis.concertinfoproject.data

import androidx.paging.DataSource
import com.nurisis.concertinfoproject.data.local.ConcertLocalDataSource
import com.nurisis.concertinfoproject.data.remote.ConcertRemoteDataSource
import com.nurisis.concertinfoproject.entity.ApiResponse
import com.nurisis.concertinfoproject.entity.ConcertItem
import kotlinx.coroutines.*

/**
 * Repository of all data used here (Remote & Local)
 * */

class ConcertRepository(
    private val concertRemoteDataSource: ConcertRemoteDataSource,
    private val concertLocalDataSource: ConcertLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseDataSource() {

    /**
     * Get the user's bookmark list from local storage(Room).
     * Using Paging library from Jetpack.
     * @return DataSource.Factory<Int, ConcertItem>
     * */
    fun getAllBookMarks() : DataSource.Factory<Int, ConcertItem> {
        return concertLocalDataSource.getAllBookMarks()
    }

    /**
     * Get performance information from Korea from public API
     * @return Result<ApiResponse>
     * */
    suspend fun getConcerts(realmCode:String, sido:String): Result<ApiResponse> {
        return withContext(ioDispatcher){
            getResult { concertRemoteDataSource.getConcerts(realmCode, sido) }
        }
    }

    /**
     * Get a bookmark contents contains title
     * @param title
     * @return Result<ConcertItem>
     * */
    suspend fun getBookMark(title : String) : Result<ConcertItem> {
        return withContext(ioDispatcher){
            try {
                Result.Success(concertLocalDataSource.getBookMark(title))
            }catch (e:java.lang.Exception) {
                Result.Error(Exception("Error on getting bookmark data from local."))
            }
        }
    }

    /**
     * Save a performance item as user's bookmark
     * @param item
     * */
    suspend fun saveBookMark(item: ConcertItem) {
        coroutineScope {
            launch { concertLocalDataSource.saveBookMark(item) }
        }
    }

    /**
     * Delete a performance item from user's bookmark
     * @param item
     * */
    suspend fun deleteBookMark(item: ConcertItem) {
        coroutineScope {
            launch { concertLocalDataSource.deleteBookMark(item) }
        }
    }
}
