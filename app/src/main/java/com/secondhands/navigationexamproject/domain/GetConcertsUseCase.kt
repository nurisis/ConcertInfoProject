package com.secondhands.navigationexamproject.domain

import android.util.Log
import com.secondhands.navigationexamproject.data.ConcertRepository
import com.secondhands.navigationexamproject.data.Result
import com.secondhands.navigationexamproject.entity.ApiMsgBody
import com.secondhands.navigationexamproject.entity.ApiResponse
import com.secondhands.navigationexamproject.entity.ConcertItem
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class GetConcertsUseCase(
    private val concertRepository: ConcertRepository
) {
    lateinit var result: Result<ApiMsgBody>
    suspend operator fun invoke() {
        val concertsResult = concertRepository.getConcerts()

        concertsResult
            .subscribeOn(Schedulers.io())
            .subscribe({
                result =  Result.Success(it)
                Log.d("LOG>>", "result on UseCase : $result")
            },{
                Log.e("LOG>>", "Error on Usecase : $it")
                result = Result.Error(Exception(it))
            })

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