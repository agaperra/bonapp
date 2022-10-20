package com.team.bonapp.domain.model.basic.full

import com.google.gson.annotations.SerializedName

data class FullHits(
    @SerializedName("recipe") val recipe : FullRecipe
    )
