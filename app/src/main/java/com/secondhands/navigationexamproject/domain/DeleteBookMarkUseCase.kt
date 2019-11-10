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
import com.secondhands.navigationexamproject.entity.ConcertItem
import io.reactivex.Single

class DeleteBookMarkUseCase(
    private val concertRepository: ConcertRepository
) {
    suspend operator fun invoke(item: ConcertItem){
        concertRepository.deleteBookMark(item = item)
    }

}


