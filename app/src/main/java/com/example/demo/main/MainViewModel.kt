package com.example.demo.main

import android.os.CountDownTimer
import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.*
import com.example.demo.data.Users
import com.example.demo.network.ApiHelper
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.TickerMode
import kotlinx.coroutines.channels.ticker
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.EmptyCoroutineContext

class MainViewModel(private val apiHelper: ApiHelper) : ViewModel() {
    private val usersMutableLiveData = MutableLiveData<List<Users>>()
    private val countMutableLiveData = MutableLiveData<Int>()
    private val statusMutableLiveData = MutableLiveData<String>()
    private val errorMutableLiveData = MutableLiveData<String>()
    val mediatorLiveData = MediatorLiveData<String>()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d("Coroutine Exception ", exception.message.toString())
        errorMutableLiveData.postValue(exception.message)
    }

    init {
        mediatorLiveData.addSource(countMutableLiveData) {
            mediatorLiveData.postValue(it.toString())
        }
        mediatorLiveData.addSource(statusMutableLiveData) {
            mediatorLiveData.postValue(it)
        }
    }

    val userNamesLiveData: LiveData<List<String>>
        get() = Transformations.map(usersMutableLiveData) { userList ->
            userList.map { it.userName }
        }

    @OptIn(ObsoleteCoroutinesApi::class)
    fun fetchUsers() {
        val count = AtomicInteger()
        val tickerChannel = ticker(delayMillis = 10_000, initialDelayMillis = 0, mode = TickerMode.FIXED_PERIOD)
        viewModelScope.launch(coroutineExceptionHandler) {
            async(coroutineExceptionHandler) {
                withTimeout(60_000) {
                    for (event in tickerChannel) {
                        usersMutableLiveData.value = async {
                            countMutableLiveData.value = count.incrementAndGet()
                            apiHelper.getUsersData()
                        }.await()
                    }
                }
            }

            async(coroutineExceptionHandler) {
                delay(20_000)
                tickerChannel.cancel()
            }
        }
    }

    @OptIn(ObsoleteCoroutinesApi::class)
    fun getApiStatus() {
        val count = AtomicInteger()
        var countValue = 0
        var status = ""
        val tickerChannel = ticker(delayMillis = 5_000, initialDelayMillis = 0, mode = TickerMode.FIXED_PERIOD)
        viewModelScope.launch(coroutineExceptionHandler) {
            async(coroutineExceptionHandler) {
                withTimeout(3 * 60_000) {
                    for (event in tickerChannel) {
                        statusMutableLiveData.value = async {
                            countValue = count.incrementAndGet()
                            countMutableLiveData.value = countValue
                            status = apiHelper.getApiStatus().status
                            if (status == READY){
                                tickerChannel.cancel()
                            }

                            "$status + $count"
                        }.await()
                    }
                }
            }
        }
    }

    companion object {
        private const val READY = "Ready"
    }
}