package com.secondhands.navigationexamproject.data

import com.secondhands.navigationexamproject.entity.ConcertItem

interface ConcertRepository {

    suspend fun getConcerts() : Result<List<ConcertItem>>

}