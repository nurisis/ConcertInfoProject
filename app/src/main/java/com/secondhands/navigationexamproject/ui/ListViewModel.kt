package com.secondhands.navigationexamproject.ui

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.secondhands.navigationexamproject.data.Result
import com.secondhands.navigationexamproject.domain.DeleteBookMarkUseCase
import com.secondhands.navigationexamproject.domain.GetBookMarkUseCase
import com.secondhands.navigationexamproject.domain.GetConcertsUseCase
import com.secondhands.navigationexamproject.domain.SaveBookMarkUseCase
import com.secondhands.navigationexamproject.entity.ApiResponse
import com.secondhands.navigationexamproject.entity.ConcertItem
import kotlinx.coroutines.launch

class ListViewModel(
    private val getConcertsUseCase: GetConcertsUseCase,
    private val getBookMarkUseCase: GetBookMarkUseCase,
    private val saveBookMarkUseCase: SaveBookMarkUseCase,
    private val deleteBookMarkUseCase: DeleteBookMarkUseCase
) : ViewModel() {

    private val _concertResponse = MutableLiveData<ApiResponse>()
    val concertResponse: LiveData<ApiResponse> = _concertResponse

    private val _concertList = MutableLiveData<List<ConcertItem>>()
    val concertList: LiveData<List<ConcertItem>> = _concertList

    private val _selectedConcertItem = MutableLiveData<ConcertItem>(ConcertItem())
    val selectedConcertItem: LiveData<ConcertItem> = _selectedConcertItem

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _toastText = MutableLiveData<String>()
    val toastText: LiveData<String> = _toastText

    private val _realmCode = MutableLiveData<String>()
    val realmCode: LiveData<String> = _realmCode

    private val _sido = MutableLiveData<String>()
    val sido: LiveData<String> = _sido

    private val _isBookMarked = MutableLiveData<Boolean>(false)
    val isBookMarked: LiveData<Boolean> = _isBookMarked

//    private val _bookMarkList = MutableLiveData<List<ConcertItem>>()
//    val bookMarkList: LiveData<List<ConcertItem>> = _bookMarkList

    val bookMarkList: LiveData<PagedList<ConcertItem>>

    init {
        // Configuration of paging library
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(10)
            .setPrefetchDistance(10)
            .setPageSize(10)
            .setEnablePlaceholders(true)
            .build()

//        dataSourceFactory = ConcertDataSourceFactory(compositeDisposable, concertApiSource, realmCode.value ?: "B000", sido.value ?: "서울")
        bookMarkList = LivePagedListBuilder(getBookMarkUseCase.getAllBookMarks(), config).build()

        loadConcerts()
    }

    fun loadConcerts() {
        _dataLoading.value = true

        viewModelScope.launch {
            val result = getConcertsUseCase.invoke()

            if(result is Result.Success) {
                _concertResponse.value = result.data
                _concertList.value = result.data.apiBody.perforList
            }
            else {
                Log.e("LOG>>","Result error from viewModel : ${result.toString()}")
            }

            _dataLoading.value = false
        }
    }

    fun clickConcertItem(item: ConcertItem) {
        // 1. Set selected item
        _selectedConcertItem.value = item

        // 2. Check if this item is bookmarked by user
        _isBookMarked.value = false
        viewModelScope.launch {
            _isBookMarked.value = getBookMarkUseCase.isItemBookMark(item)
        }
    }

    fun saveBookMark() {
        selectedConcertItem.value?.let {
            Log.d("LOG>>","isBookMarked : ${isBookMarked.value}")
            viewModelScope.launch {
                // 1. Check already bookmarked or not
                // Already bookmarked => remove it.
                if(isBookMarked.value!!) {
                    Log.d("LOG>>","Bookmark Delete !")
                    deleteBookMarkUseCase.invoke(it)

                    _isBookMarked.value = false
                    _toastText.value = "Deleting a bookmark success :)"
                }
                // Save it as a bookmark
                else {
                    Log.d("LOG>>","Bookmark Save !")
                    saveBookMarkUseCase.invoke(it)

                    _isBookMarked.value = true
                    _toastText.value = "Saving a bookmark success :)"
                }
            }

        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("LOG>>","onCleared -----------")
    }
}
