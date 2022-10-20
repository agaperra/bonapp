package com.team.bonapp.domain.repository

import com.team.bonapp.domain.model.basic.Recipe
import kotlinx.coroutines.flow.Flow

interface EdamamRepository {

    suspend fun mainContent(
        app_id: String,
        app_key: String,
        query: String,
        dishType: String
    ): List<Recipe>
}