package com.secondhands.navigationexamproject.domain

import android.util.Log
import com.secondhands.navigationexamproject.data.ConcertRepository
import com.secondhands.navigationexamproject.data.Result
import com.secondhands.navigationexamproject.entity.ApiResponse
import io.reactivex.schedulers.Schedulers
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException
import java.lang.Exception
import androidx.databinding.adapters.NumberPickerBindingAdapter.setValue
import androidx.paging.DataSource
import com.secondhands.navigationexamproject.entity.ConcertItem
import io.reactivex.Single

class GetBookMarkUseCase(
    private val concertRepository: ConcertRepository
) {
//    suspend operator fun invoke() : Result<ApiResponse> {
//        return concertRepository.getConcerts()
//    }

    suspend fun isItemBookMark(item: ConcertItem) : Boolean {
        val result = concertRepository.getBookMark(item.title)
        if(result is Result.Success) {
            if(result.data != null) return true
        }

        return false
    }

    fun getAllBookMarks() : DataSource.Factory<Int,ConcertItem> {
        val result = concertRepository.getAllBookMarks()
        Log.d("LOG>>", "Bookmark All result : $result")
        return result
    }
}


