package com.kotlinmvvmstructure.base

import androidx.lifecycle.ViewModel
import com.kotlinmvvmstructure.SingleLiveEvent

open class BaseViewModel: ViewModel() {

    val statusMessage = SingleLiveEvent<String>()

}