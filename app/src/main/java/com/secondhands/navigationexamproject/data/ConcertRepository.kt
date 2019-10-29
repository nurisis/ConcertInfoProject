package com.secondhands.navigationexamproject.data

import com.secondhands.navigationexamproject.entity.ConcertItem
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface ConcertRepository {

    suspend fun getConcerts() : Single<List<ConcertItem>>

}

class ConcertRepositoryImpl(private val concertDatasource: ConcertDatasource) : ConcertRepository {
    override suspend fun getConcerts(): Single<List<ConcertItem>> =
        concertDatasource.getConcerts(
            "H2vKl%2BMCf8RlJBTobMych6%2F7GSO%2Byxez9WozIFxaHRDymKZOPFH2S1cKwFTlaxZfqkoO0SBE4YlBLy%2BWG9Rnfw%3D%3D"
            ,1
            ,"B000"
            ,"20191029"
            ,"1"
            ,"10"
            ,"서울"
        )
}