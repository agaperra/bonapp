package com.team.bonapp.data.api

import com.team.bonapp.domain.model.basic.Edamam
import retrofit2.http.GET
import retrofit2.http.Query

interface EdamamApi {


    @GET("api/recipes/v2")
    fun mainContent(
        @Query("q", encoded = true) q: String,
        @Query("dishType", encoded = true) dishType: String,
    ): Edamam

}