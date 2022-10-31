package com.team.bonapp.domain.repository

import com.team.bonapp.domain.model.basic.Edamam
import com.team.bonapp.domain.model.basic.Recipe
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface EdamamRepository {

    suspend fun mainContent(
        app_id: String,
        app_key: String,
        query: String,
        dishType: String
    ): List<Recipe>

    suspend fun filteredContent(
        type: String = "public",
        query: String,
        app_id: String,
        app_key: String,
        diet: Array<String>?,
        dishType: Array<String>?,
        health: Array<String>?,
        cuisineType: Array<String>?,
        mealType: Array<String>?
    ): List<Recipe>
}