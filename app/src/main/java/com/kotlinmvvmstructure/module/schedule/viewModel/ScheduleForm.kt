package com.kotlinmvvmstructure.module.schedule.viewModel

import androidx.databinding.Bindable
import com.kotlinmvvmstructure.BR
import com.kotlinmvvmstructure.base.ParentBaseObservable
import com.kotlinmvvmstructure.module.schedule.model.ScheduleListModel
import com.kotlinmvvmstructure.utils.BindableDelegates
import java.util.ArrayList


class ScheduleForm : ParentBaseObservable() {
    @get:Bindable
    var scheduleList: ArrayList<ScheduleListModel> by BindableDelegates(ArrayList(), BR.scheduleList)

}