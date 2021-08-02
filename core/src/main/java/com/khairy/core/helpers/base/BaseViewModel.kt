package com.khairy.core.helpers.base
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khairy.core.helpers.livedata.SingleLiveEvent
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by khairy on ر, 22/ماي/2019 at 01:54 م.
 * mohamed.khairy@apptcom.com
 */
open class BaseViewModel : ViewModel(){
    val dataLoading = MutableLiveData<Boolean>()
    val showNoNetworkScreenEvent = SingleLiveEvent<Boolean>()
    val showServerIssueEvent = SingleLiveEvent<Boolean>()
    val showNoListScreenEvent = SingleLiveEvent<Boolean>()
    var showErrorMessageEvent = SingleLiveEvent<String?>()
    var errorResourceMessageEvent = SingleLiveEvent<Int>()
    var successMessageEvent = SingleLiveEvent<String?>()
    var executor: Executor = Executors.newFixedThreadPool(5)

}