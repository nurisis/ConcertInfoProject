package com.nurisis.concertinfoproject.domain

import com.nurisis.concertinfoproject.data.ConcertRepository
import com.nurisis.concertinfoproject.entity.ConcertItem

class SaveBookMarkUseCase(
    private val concertRepository: ConcertRepository
) {
    suspend operator fun invoke(item: ConcertItem){
        concertRepository.saveBookMark(item = item)
    }

}


