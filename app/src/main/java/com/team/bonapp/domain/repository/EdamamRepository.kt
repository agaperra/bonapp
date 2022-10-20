package com.team.bonapp.domain.repository

import com.team.bonapp.domain.model.basic.Edamam
import com.team.bonapp.domain.model.basic.Hits
import com.team.bonapp.domain.model.basic.Recipe

interface EdamamRepository {

    fun mainContent(
        app_id: String,
        app_key: String,
        query: String,
        dishType: String
    ): List<Hits>
}