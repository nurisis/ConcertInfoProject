package com.secondhands.navigationexamproject.ui

import android.util.Log
import androidx.lifecycle.*
import com.secondhands.navigationexamproject.domain.GetConcertsUseCase
import com.secondhands.navigationexamproject.entity.ApiResponse
import kotlinx.coroutines.launch

class ListViewModel(
    private val getConcertsUseCase: GetConcertsUseCase
) : ViewModel() {

    private val _concertResponse = MutableLiveData<ApiResponse>()
    val concertResponse: LiveData<ApiResponse> = _concertResponse

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _toastText = MutableLiveData<String>()
    val toastText: LiveData<String> = _toastText

//    init {
//        loadConcerts()
//    }

    fun loadConcerts() {
        Log.d("LOG>>", "--------- loadConcerts ----------")
        _dataLoading.value = true

        viewModelScope.launch {
            val concertsResult = getConcertsUseCase.invoke()

//            if(concertsResult is Result.Success){
//                _concertLists.value = concertsResult.data
//                Log.d("LOG>>", "data : ${concertsResult.data}")
//            }
//            else {
//                _concertLists.value = emptyList()
//                _toastText.value = "An error occurred while retrieving the data\uD83E\uDD76"
//            }

            _dataLoading.value = false
        }
    }

}
