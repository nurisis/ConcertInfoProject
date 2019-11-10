package com.secondhands.concertinfoproject.domain

import com.secondhands.concertinfoproject.data.ConcertRepository
import com.secondhands.concertinfoproject.data.Result
import androidx.paging.DataSource
import com.secondhands.concertinfoproject.entity.ConcertItem

class GetBookMarkUseCase(
    private val concertRepository: ConcertRepository
) {

    /**
     * To check if a particular item is bookmarked
     * @param item
     * @return Boolean
     * */
    suspend fun isItemBookMark(item: ConcertItem) : Boolean {
        val result = concertRepository.getBookMark(item.title)
        if(result is Result.Success) {
            if(result.data != null) return true
        }

        return false
    }

    fun getAllBookMarks() : DataSource.Factory<Int,ConcertItem> {
        val result = concertRepository.getAllBookMarks()
        return result
    }
}


