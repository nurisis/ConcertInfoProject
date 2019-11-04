package com.secondhands.navigationexamproject.ui

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.secondhands.navigationexamproject.data.ConcertDataSourceFactory
import com.secondhands.navigationexamproject.data.ConcertDatasource
import com.secondhands.navigationexamproject.domain.GetConcertsUseCase
import com.secondhands.navigationexamproject.entity.ApiResponse
import com.secondhands.navigationexamproject.entity.ConcertItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ListViewModel(
    private val getConcertsUseCase: GetConcertsUseCase,
    private val concertDatasource: ConcertDatasource
) : ViewModel() {

    private val _concertResponse = MutableLiveData<ApiResponse>()
    val concertResponse: LiveData<ApiResponse> = _concertResponse

    private val _concertList = MutableLiveData<List<ConcertItem>>()
    val concertList: LiveData<List<ConcertItem>> = _concertList

    val conCertLiveData: LiveData<PagedList<ConcertItem>>

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _toastText = MutableLiveData<String>()
    val toastText: LiveData<String> = _toastText

    private val _realmCode = MutableLiveData<String>()
    val realmCode: LiveData<String> = _realmCode

    private val _sido = MutableLiveData<String>()
    val sido: LiveData<String> = _sido

    private val compositeDisposable = CompositeDisposable()
    private val dataSourceFactory:ConcertDataSourceFactory

    init {
        // Configuration of paging
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(20)
            .setPrefetchDistance(20)
            .setPageSize(20)
            .setEnablePlaceholders(true)
            .build()

        dataSourceFactory = ConcertDataSourceFactory(compositeDisposable, concertDatasource, realmCode.value ?: "B000", sido.value ?: "서울")
        conCertLiveData = LivePagedListBuilder(dataSourceFactory, config).build()

//        loadConcerts()
    }

    fun loadConcerts() {
        _dataLoading.value = true

        viewModelScope.launch {
            getConcertsUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _concertResponse.value = it
                    _concertList.value = it.apiBody.perforList
                },{
                    Log.e("LOG>>", "Error : $it")
                })

            _dataLoading.value = false
        }
    }

}
