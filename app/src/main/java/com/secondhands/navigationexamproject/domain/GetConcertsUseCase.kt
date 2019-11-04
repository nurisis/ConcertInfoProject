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
import io.reactivex.Single

class GetConcertsUseCase(
    private val concertRepository: ConcertRepository
) {
    suspend operator fun invoke() : Single<ApiResponse> {
        return concertRepository.getConcerts()
    }

}


