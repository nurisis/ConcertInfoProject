package com.nurisis.concertinfoproject.domain

import com.nurisis.concertinfoproject.data.ConcertRepository
import com.nurisis.concertinfoproject.entity.ConcertItem

class DeleteBookMarkUseCase(
    private val concertRepository: ConcertRepository
) {
    suspend operator fun invoke(item: ConcertItem){
        concertRepository.deleteBookMark(item = item)
    }

}


