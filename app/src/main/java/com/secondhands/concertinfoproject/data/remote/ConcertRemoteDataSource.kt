package com.secondhands.concertinfoproject.data.remote

import com.secondhands.concertinfoproject.entity.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

const val SERVICE_KEY = "H2vKl%2BMCf8RlJBTobMych6%2F7GSO%2Byxez9WozIFxaHRDymKZOPFH2S1cKwFTlaxZfqkoO0SBE4YlBLy%2BWG9Rnfw%3D%3D"

class ConcertRemoteDataSource(
    private val concertApiSource: ConcertApiSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getConcerts(realmCode:String, sido:String) : Response<ApiResponse> = withContext(ioDispatcher) {
        concertApiSource.getConcerts(
            SERVICE_KEY
            ,1
            ,realmCode
            , SimpleDateFormat("yyyyMMdd").format(Date()) // Date of today
            ,"1"
            ,"60"
            ,sido
        )
    }

}