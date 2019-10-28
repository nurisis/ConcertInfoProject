package com.secondhands.navigationexamproject.data

import com.secondhands.navigationexamproject.entity.ConcertItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ConcertDatasource {

    @GET("/openapi/rest/publicperformancedisplays/realm")
    @Headers("Content-type: application/json")
    fun getConcerts(@Query("serviceKey") serviceKey: String
                    , @Query("sortStdr") sortStdr:Int = 1
                    , @Query("realmCode") realmCode: String
                    , @Query("from") from:String
                    , @Query("cPage") cPage:String
                    , @Query("rows") rows:String
                    , @Query("sido") sido:String
    ): Single<List<ConcertItem>>

}