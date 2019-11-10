//package com.secondhands.navigationexamproject.data
//
//import android.util.Log
//import androidx.lifecycle.MutableLiveData
//import androidx.paging.DataSource
//import androidx.paging.PageKeyedDataSource
//import com.secondhands.navigationexamproject.data.remote.ConcertApiSource
//import com.secondhands.navigationexamproject.entity.ConcertItem
//import io.reactivex.android.schedulers.AndroidSchedulers
//import io.reactivex.disposables.CompositeDisposable
//import io.reactivex.schedulers.Schedulers
//import java.text.SimpleDateFormat
//import java.util.*
//
//const val SERVICE_KEY = "H2vKl%2BMCf8RlJBTobMych6%2F7GSO%2Byxez9WozIFxaHRDymKZOPFH2S1cKwFTlaxZfqkoO0SBE4YlBLy%2BWG9Rnfw%3D%3D"
//val START_DATE = SimpleDateFormat("yyyyMMdd").format(Date())
//
//class ConcertDataSourceFactory(
//    private val compositeDisposable: CompositeDisposable,
//    private val concertApiSource: ConcertApiSource,
//    private val realmCode:String,
//    private val sido:String
//)
//    : DataSource.Factory<Int, ConcertItem>() {
//
//    val datataSourceLiveData = MutableLiveData<ConcertDataSource>()
//
//    override fun create(): DataSource<Int, ConcertItem> {
//        val newsDataSource = ConcertDataSource(compositeDisposable, concertApiSource, realmCode, sido)
//        datataSourceLiveData.postValue(newsDataSource)
//        return newsDataSource
//    }
//}
//
//class ConcertDataSource(
//    private val compositeDisposable: CompositeDisposable,
//    private val concertApiSource: ConcertApiSource,
//    private val realmCode:String,
//    private val sido:String
//) : PageKeyedDataSource<Int,ConcertItem>() {
//
//    private var cPage = 1
//
//    override fun loadInitial(
//        params: LoadInitialParams<Int>,
//        callback: LoadInitialCallback<Int, ConcertItem>
//    ) {
//        Log.d("LOG>>","Date : $START_DATE")
//        compositeDisposable.add(
//            concertApiSource.getConcerts(
//            SERVICE_KEY,
//            1,
//            realmCode,
//            START_DATE,
//            cPage.toString(),
//            "20",
//            sido
//            )
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                callback.onResult(it.apiBody.perforList, cPage, it.apiBody.perforList.size)
//                cPage++
//            },{
//                Log.e("LOG>>","Error : $it")
//            })
//        )
//    }
//
//    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ConcertItem>) {
//        compositeDisposable.add(
//            concertApiSource.getConcerts(
//                SERVICE_KEY,
//                1,
//                realmCode,
//                START_DATE,
//                cPage.toString(),
//                "20",
//                sido
//            )
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    callback.onResult(it.apiBody.perforList,cPage)
//                    cPage++
//                },{
//                    Log.e("LOG>>","Error2 : $it")
//                })
//        )
//    }
//
//    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ConcertItem>) {
//    }
//
//}
//
//
