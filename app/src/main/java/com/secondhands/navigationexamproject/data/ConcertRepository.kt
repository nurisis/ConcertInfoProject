package com.secondhands.navigationexamproject.data

import androidx.paging.DataSource
import com.secondhands.navigationexamproject.data.local.ConcertLocalDataSource
import com.secondhands.navigationexamproject.data.remote.ConcertApiSource
import com.secondhands.navigationexamproject.data.remote.ConcertRemoteDataSource
import com.secondhands.navigationexamproject.entity.ApiResponse
import com.secondhands.navigationexamproject.entity.ConcertItem
import io.reactivex.Single
import kotlinx.coroutines.*

class ConcertRepository(
    private val concertRemoteDataSource: ConcertRemoteDataSource,
    private val concertLocalDataSource: ConcertLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseDataSource() {

    fun getAllBookMarks() : DataSource.Factory<Int, ConcertItem> {
        return concertLocalDataSource.getAllBookMarks()
    }
    suspend fun getConcerts(): Result<ApiResponse> {
        return withContext(ioDispatcher){
            getResult { concertRemoteDataSource.getConcerts() }
        }
    }

    suspend fun getBookMark(title : String) : Result<ConcertItem> {
        return withContext(ioDispatcher){
            try {
                Result.Success(concertLocalDataSource.getBookMark(title))
            }catch (e:java.lang.Exception) {
                Result.Error(Exception("Error on getting bookmark data from local."))
            }
        }
    }

    suspend fun saveBookMark(item: ConcertItem) {
        coroutineScope {
            launch { concertLocalDataSource.saveBookMark(item) }
        }
    }

    suspend fun deleteBookMark(item: ConcertItem) {
        coroutineScope {
            launch { concertLocalDataSource.deleteBookMark(item) }
        }
    }
}
