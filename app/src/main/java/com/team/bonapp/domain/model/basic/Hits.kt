package com.team.bonapp.domain.model.basic

import com.google.gson.annotations.SerializedName


data class Hits (

	@SerializedName("recipe") var recipe : Recipe
)