package com.team.bonapp.data.api

import com.team.bonapp.domain.AppState
import com.team.bonapp.domain.model.basic.Edamam
import retrofit2.http.GET
import retrofit2.http.Query

interface EdamamApi {


    @GET("api/recipes/v2")
    suspend fun mainContent(
        @Query("app_id", encoded = true) app_id: String,
        @Query("app_key", encoded = true) app_key: String,
        @Query("q", encoded = true) q: String,
        @Query("dishType", encoded = true) dishType: String,
    ): Edamam

}