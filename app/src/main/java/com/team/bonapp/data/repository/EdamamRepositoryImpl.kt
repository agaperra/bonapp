package com.team.bonapp.data.repository

import com.team.bonapp.data.api.EdamamApi
import com.team.bonapp.data.mapper.toHits
import com.team.bonapp.data.mapper.toRecipe
import com.team.bonapp.domain.model.basic.Recipe
import com.team.bonapp.domain.repository.EdamamRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class EdamamRepositoryImpl @Inject constructor(
    private val edamamApi: EdamamApi
) : EdamamRepository {

    override suspend fun mainContent(
        app_id: String,
        app_key: String,
        query: String,
        dishType: String
    ): List<Recipe> =
        edamamApi.mainContent(app_id = app_id, app_key = app_key, q = query, dishType = dishType).toHits().map { it.toRecipe() }

}
