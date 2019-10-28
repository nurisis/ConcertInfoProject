package com.secondhands.navigationexamproject.domain

import com.secondhands.navigationexamproject.data.ConcertRepository
import com.secondhands.navigationexamproject.data.Result
import com.secondhands.navigationexamproject.entity.ConcertItem

class GetConcertsUseCase(
    private val concertRepository: ConcertRepository
) {
    suspend operator fun invoke() : Result<List<ConcertItem>> {

        val concertsResult = concertRepository.getConcerts()

        if(concertsResult is Result.Success) {
            val concerts = concertsResult.data
            // The reason of using mutableListOf : To make a list mutable.
            val concertsToShow = mutableListOf<ConcertItem>()
            concertsToShow.addAll(concerts)

            return Result.Success(concertsToShow)
        }

        return concertsResult
    }
}