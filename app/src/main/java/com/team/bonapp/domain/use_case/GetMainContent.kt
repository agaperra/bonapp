package com.team.bonapp.domain.use_case

import com.team.bonapp.data.mapper.toRecipe
import com.team.bonapp.domain.model.basic.Recipe
import com.team.bonapp.domain.repository.EdamamRepository
import javax.inject.Inject

class GetMainContent @Inject constructor(private val edamamRepository: EdamamRepository) {

    operator fun invoke(
        app_id: String,
        app_key: String,
        query: String,
        dishType: String
    ): List<Recipe> = edamamRepository.mainContent(
        app_id = app_id,
        app_key = app_key,
        query = query,
        dishType = dishType
    ).map { it.toRecipe() }

}