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



class GetConcertsUseCase(
    private val concertRepository: ConcertRepository
) {
    lateinit var result: Result<ResponseBody>

    suspend operator fun invoke() {

        concertRepository.getConcerts()
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("LOG>>","body : ${it}")
            },{
                Log.e("LOG>>","Error : $it")
            })


//        concertsResult
//            .subscribeOn(Schedulers.io())
//            .subscribe({
//                result =  Result.Success(it)
//                Log.d("LOG>>", "result on UseCase : ${it.string()}")
//            },{
//                Log.e("LOG>>", "Error on Usecase : $it")
//                result = Result.Error(Exception(it))
//            })

    }

//    suspend operator fun invoke() : Result<List<ConcertItem>> {
//        val concertsResult = concertRepository.getConcerts()
//
//        concertsResult
//            .subscribeOn(Schedulers.io())
//            .subscribe({
//            result =  Result.Success(it)
//            Log.d("LOG>>", "result on UseCase : $result")
//        },{
//            Log.e("LOG>>", "Error on Usecase : $it")
//            result = Result.Error(Exception(it))
//        })
//
//        return result
//
////        concertsResult.doOnSuccess {
////            Log.d("LOG>>", "Success : $it")
////            val concerts = it
////            // The reason of using mutableListOf : To make a list mutable.
////            val concertsToShow = mutableListOf<ConcertItem>()
////            concertsToShow.addAll(concerts)
////
////            Result.Success(concertsToShow)
////        }
////            .doOnError {
////                Log.d("LOG>>", "Error : $it")
////                result = Result.Error(Exception(it))
////            }
//
//
////        if(concertsResult is Result.Success) {
////            val concerts = concertsResult.data
////            // The reason of using mutableListOf : To make a list mutable.
////            val concertsToShow = mutableListOf<ConcertItem>()
////            concertsToShow.addAll(concerts)
////
////            return Result.Success(concertsToShow)
////        }
////
////        return concertsResult
//    }
}


