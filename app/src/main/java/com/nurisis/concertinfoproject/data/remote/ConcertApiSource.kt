package com.nurisis.concertinfoproject.data.remote

import com.nurisis.concertinfoproject.entity.ApiResponse
import retrofit2.Response
import retrofit2.http.*

interface ConcertApiSource {

    /**
     * Korea's public data of performance.
     * @return ApiResponse
     * */
    @GET("/openapi/rest/publicperformancedisplays/realm")
    suspend fun getConcerts(@Query("serviceKey", encoded = true) serviceKey: String
                    , @Query("sortStdr") sortStdr:Int = 1
                    , @Query("realmCode") realmCode: String
                    , @Query("from") from:String
                    , @Query("cPage") cPage:String
                    , @Query("rows") rows:String
                    , @Query("sido", encoded = true) sido:String
    ): Response<ApiResponse>
}