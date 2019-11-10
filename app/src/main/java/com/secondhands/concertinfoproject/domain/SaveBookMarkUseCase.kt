package com.secondhands.concertinfoproject.domain

import com.secondhands.concertinfoproject.data.ConcertRepository
import com.secondhands.concertinfoproject.entity.ConcertItem

class SaveBookMarkUseCase(
    private val concertRepository: ConcertRepository
) {
    suspend operator fun invoke(item: ConcertItem){
        concertRepository.saveBookMark(item = item)
    }

}


