package com.kotlinmvvmstructure.module.engineersList.model

import com.kotlinmvvmstructure.module.engineersList.response.EngineersListResponse
import java.io.Serializable

class EngineerModel(): Serializable {

    var id: Int = 0

    var name: String = ""

    constructor(data: EngineersListResponse.Engineers): this(){

        if (data.id != null)
            this.id = data.id!!

        if (data.name!=null)
            this.name = data.name!!
    }
}