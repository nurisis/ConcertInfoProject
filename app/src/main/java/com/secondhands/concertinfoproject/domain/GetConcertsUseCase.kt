package com.secondhands.concertinfoproject.domain

import com.secondhands.concertinfoproject.data.ConcertRepository
import com.secondhands.concertinfoproject.data.Result
import com.secondhands.concertinfoproject.entity.ApiResponse

class GetConcertsUseCase(
    private val concertRepository: ConcertRepository
) {
    suspend operator fun invoke(realmCode:String, sido:String) : Result<ApiResponse> {
        return concertRepository.getConcerts(realmCode, sido)
    }

}


