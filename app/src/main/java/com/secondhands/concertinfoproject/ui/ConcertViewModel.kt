package com.secondhands.concertinfoproject.ui

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.secondhands.concertinfoproject.data.Result
import com.secondhands.concertinfoproject.domain.DeleteBookMarkUseCase
import com.secondhands.concertinfoproject.domain.GetBookMarkUseCase
import com.secondhands.concertinfoproject.domain.GetConcertsUseCase
import com.secondhands.concertinfoproject.domain.SaveBookMarkUseCase
import com.secondhands.concertinfoproject.entity.ApiResponse
import com.secondhands.concertinfoproject.entity.ConcertItem
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ConcertViewModel(
    private val getConcertsUseCase: GetConcertsUseCase,
    private val getBookMarkUseCase: GetBookMarkUseCase,
    private val saveBookMarkUseCase: SaveBookMarkUseCase,
    private val deleteBookMarkUseCase: DeleteBookMarkUseCase
) : ViewModel() {

    // LiveData of whole performance response from public API
    private val _concertResponse = MutableLiveData<ApiResponse>()
    val concertResponse: LiveData<ApiResponse> = _concertResponse

    // LiveData of list of performances from public API
    private val _concertList = MutableLiveData<List<ConcertItem>>()
    val concertList: LiveData<List<ConcertItem>> = _concertList

    // LiveData of the item the user selects from the list.
    private val _selectedConcertItem = MutableLiveData<ConcertItem>()
    val selectedConcertItem: LiveData<ConcertItem> = _selectedConcertItem

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _toastText = MutableLiveData<String>()
    val toastText: LiveData<String> = _toastText

    // LiveData of the type code(parameters of public data)
    private val _realmCode = MutableLiveData<String>("B000")
    val realmCode: LiveData<String> = _realmCode

    // LiveData of the country(parameters of public data)
    private val _sido = MutableLiveData<String>("서울")
    val sido: LiveData<String> = _sido

    // LiveData of checking whether a selectedConcertItem is a bookmarked
    private val _isBookMarked = MutableLiveData<Boolean>(false)
    val isBookMarked: LiveData<Boolean> = _isBookMarked

    // LiveData of list of the user's bookmark. Using Paging library
    val bookMarkList: LiveData<PagedList<ConcertItem>>

    init {
        // Configuration of paging library
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(10)
            .setPrefetchDistance(10)
            .setPageSize(10)
            .setEnablePlaceholders(true)
            .build()

        bookMarkList = LivePagedListBuilder(getBookMarkUseCase.getAllBookMarks(), config).build()

        loadConcerts()
    }

    /**
     *  Fetch a list of performances from public API
     * */
    fun loadConcerts() {
        _dataLoading.value = true

        viewModelScope.launch {
            val result = getConcertsUseCase.invoke(realmCode.value!!, sido.value!!)

            if(result is Result.Success) {
                _concertResponse.value = result.data
                _concertList.value = result.data.apiBody.perforList
            }
            else {
                Log.e("LOG>>","Result error from viewModel : ${result}")
            }

            _dataLoading.value = false
        }
    }

    /**
     *  When user click a item from list of performances.
     *  @param item
     * */
    fun clickConcertItem(item: ConcertItem) {
        // 1. Set selected item
        _selectedConcertItem.value = item

        // 2. Check if this item is already bookmarked by user
        _isBookMarked.value = false
        viewModelScope.launch {
            _isBookMarked.value = getBookMarkUseCase.isItemBookMark(item)
        }
    }

    /**
     *  When user click a bookmark icon on detail page.
     * */
    fun saveBookMark() {
        selectedConcertItem.value?.let {
            viewModelScope.launch {
                // Check already bookmarked or not
                // Already bookmarked => remove it.
                if(isBookMarked.value!!) {
                    deleteBookMarkUseCase.invoke(it)

                    _isBookMarked.value = false
                    _toastText.value = "Deleting a bookmark success \uD83D\uDE4C"
                }
                // Save it as a bookmark
                else {
                    it.created_time = SimpleDateFormat("yyMMdd_HHmmss").format(Date())
                    saveBookMarkUseCase.invoke(it)

                    _isBookMarked.value = true
                    _toastText.value = "Saving a bookmark success \uD83D\uDE4C"
                }
            }

        }
    }

}
