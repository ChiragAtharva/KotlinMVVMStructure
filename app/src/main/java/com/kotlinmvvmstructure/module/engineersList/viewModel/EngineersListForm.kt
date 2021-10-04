package com.kotlinmvvmstructure.module.engineersList.viewModel


import androidx.databinding.Bindable
import com.kotlinmvvmstructure.BR
import com.kotlinmvvmstructure.base.ParentBaseObservable
import com.kotlinmvvmstructure.module.engineersList.model.EngineerModel
import com.kotlinmvvmstructure.utils.BindableDelegates
import java.util.ArrayList

class EngineersListForm : ParentBaseObservable() {
    @get:Bindable
    var engineersList: ArrayList<EngineerModel> by BindableDelegates(ArrayList(), BR.engineersList)

}