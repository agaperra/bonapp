package com.team.bonapp.domain.model.basic

import com.google.gson.annotations.SerializedName

data class Recipe (

	@SerializedName("uri") val uri : String,
	@SerializedName("label") val label : String,
	@SerializedName("image") val image : String,
	@SerializedName("url") val url : String,
	@SerializedName("calories") val calories : Double,
	@SerializedName("totalWeight") val totalWeight : Double,
	@SerializedName("totalTime") val totalTime : Int
)