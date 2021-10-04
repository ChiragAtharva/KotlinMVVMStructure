package com.kotlinmvvmstructure.module.engineersList.viewModel

import com.kotlinmvvmstructure.base.BaseViewModel
import com.kotlinmvvmstructure.module.engineersList.model.EngineerModel
import com.kotlinmvvmstructure.module.engineersList.response.EngineersListResponse
import com.kotlinmvvmstructure.module.schedule.model.ScheduleListModel
import com.kotlinmvvmstructure.net.Api
import com.kotlinmvvmstructure.net.BaseCallback

/**
 * Created on 08/01/2020.
 * <p>
 * EngineersListViewModel where the business logic for fetching list of engineers.
 *
 * @see EngineerModel
 * @see ScheduleListModel
 */
class EngineersListViewModel: BaseViewModel()  {

    val engineersListForm = EngineersListForm()

    fun fetchEngineersList(){

        engineersListForm.loadingProgress = true

        Api.getApi()?.getEngineers()!!.enqueue(
            BaseCallback(object :
                BaseCallback.OnCallback {
                override fun status(success: Boolean, data: Any) {
                    engineersListForm.loadingProgress = false
                    if (success) {
                        val engineersListResponse = data as EngineersListResponse
                        val engineers: ArrayList<EngineerModel> = ArrayList()

                        if (engineersListResponse.engineers != null){
                            for (item in engineersListResponse.engineers!!)
                                engineers.add(EngineerModel(item))
                        }

                        engineersListForm.engineersList = engineers
                    } else
                        statusMessage.value = data as String
                }
            })
        )
    }

    fun getEngineers(): ArrayList<EngineerModel> {
        return engineersListForm.engineersList
    }
}