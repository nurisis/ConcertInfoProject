package com.secondhands.navigationexamproject.data

import com.secondhands.navigationexamproject.entity.ApiMsgBody
import com.secondhands.navigationexamproject.entity.ApiResponse
import com.secondhands.navigationexamproject.entity.ConcertItem
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.net.URLEncoder

interface ConcertRepository {

//    suspend fun getConcerts() : Single<List<ConcertItem>>
    suspend fun getConcerts() : Single<ApiMsgBody>
}

class ConcertRepositoryImpl(private val concertDatasource: ConcertDatasource) : ConcertRepository {
    final val SERVICE_KEY = "H2vKl%2BMCf8RlJBTobMych6%2F7GSO%2Byxez9WozIFxaHRDymKZOPFH2S1cKwFTlaxZfqkoO0SBE4YlBLy%2BWG9Rnfw%3D%3D"

    override suspend fun getConcerts(): Single<ApiMsgBody> =
        concertDatasource.getConcerts(
            SERVICE_KEY
            ,1
            ,"B000"
            ,"20191029"
            ,"1"
            ,"10"
            ,"서울"
        )
}