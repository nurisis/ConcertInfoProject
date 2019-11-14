package com.nurisis.concertinfoproject.domain

import com.nurisis.concertinfoproject.data.ConcertRepository
import com.nurisis.concertinfoproject.data.Result
import com.nurisis.concertinfoproject.entity.ApiResponse

class GetConcertsUseCase(
    private val concertRepository: ConcertRepository
) {
    suspend operator fun invoke(realmCode:String, sido:String) : Result<ApiResponse> {
        return concertRepository.getConcerts(realmCode, sido)
    }

}


