package com.team.bonapp.data.api

import com.team.bonapp.domain.model.basic.Edamam
import retrofit2.http.GET
import retrofit2.http.Query

interface EdamamApi {

    @GET("api/recipes/v2")
    suspend fun mainContent(
        @Query("type", encoded = true) type: String = "public",
        @Query("q", encoded = true) q: String,
        @Query("app_id", encoded = true) app_id: String,
        @Query("app_key", encoded = true) app_key: String,
        @Query("dishType", encoded = true) dishType: String,
    ): Edamam

    @GET("api/recipes/v2")
    suspend fun filteredContent(
        @Query("type", encoded = true) type: String = "public",
        @Query("q", encoded = true) q: String,
        @Query("app_id", encoded = true) app_id: String,
        @Query("app_key", encoded = true) app_key: String,
        @Query("diet", encoded = true) diet: Array<String>?,
        @Query("dishType", encoded = true) dishType: Array<String>?,
        @Query("health", encoded = true) health: Array<String>?,
        @Query("cuisineType", encoded = true) cuisineType: Array<String>?,
        @Query("mealType", encoded = true) mealType: Array<String>?
    ): Edamam
}