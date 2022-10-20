package com.team.bonapp.data.mapper

import com.team.bonapp.domain.model.basic.Edamam
import com.team.bonapp.domain.model.basic.Hits
import com.team.bonapp.domain.model.basic.Recipe

@JvmName("toHitsEdamamResponse")
fun Edamam.toHits(): List<Hits> = hits.map { hits ->
    Hits(
        hits.recipe
    )
}

@JvmName("toRecipeHits")
fun Hits.toRecipe(): Recipe = Recipe(
    this.recipe.uri,
    this.recipe.label,
    this.recipe.image,
    this.recipe.url,
    this.recipe.calories,
    this.recipe.totalWeight,
    this.recipe.totalTime
)