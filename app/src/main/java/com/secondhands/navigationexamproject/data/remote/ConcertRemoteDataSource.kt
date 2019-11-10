package com.secondhands.navigationexamproject.data.remote

import androidx.paging.DataSource
import com.secondhands.navigationexamproject.entity.ApiResponse
import com.secondhands.navigationexamproject.entity.ConcertItem
import io.reactivex.Single
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

const val SERVICE_KEY = "H2vKl%2BMCf8RlJBTobMych6%2F7GSO%2Byxez9WozIFxaHRDymKZOPFH2S1cKwFTlaxZfqkoO0SBE4YlBLy%2BWG9Rnfw%3D%3D"

class ConcertRemoteDataSource(
    private val concertApiSource: ConcertApiSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getConcerts() : Response<ApiResponse> = withContext(ioDispatcher) {
        concertApiSource.getConcerts(
            SERVICE_KEY
            ,1
            ,"B000"
            ,"20191029"
            ,"1"
            ,"60"
            ,"서울"
        )
    }

}