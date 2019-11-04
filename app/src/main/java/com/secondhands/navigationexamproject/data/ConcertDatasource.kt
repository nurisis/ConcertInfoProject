package com.secondhands.navigationexamproject.data

import com.secondhands.navigationexamproject.entity.ApiResponse
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ConcertDatasource {

    @GET("/openapi/rest/publicperformancedisplays/realm")
    fun getConcerts(@Query("serviceKey", encoded = true) serviceKey: String
                    , @Query("sortStdr") sortStdr:Int = 1
                    , @Query("realmCode") realmCode: String
                    , @Query("from") from:String
                    , @Query("cPage") cPage:String
                    , @Query("rows") rows:String
                    , @Query("sido", encoded = true) sido:String
    ): Single<ApiResponse>

}