package com.kotlinmvvmstructure.base

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.kotlinmvvmstructure.BR
import com.kotlinmvvmstructure.utils.BindableDelegates

open class ParentBaseObservable : BaseObservable() {

    @get:Bindable
    var loadingProgress: Boolean by BindableDelegates(false, BR.loadingProgress)
}