package com.team.bonapp.domain.model.basic

import com.google.gson.annotations.SerializedName


data class Ingredients (

		@SerializedName("text") val text : String,
		@SerializedName("weight") val weight : Double,
		@SerializedName("foodCategory") val foodCategory : String,
		@SerializedName("foodId") val foodId : String,
		@SerializedName("image") val image : String
)