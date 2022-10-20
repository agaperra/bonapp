package com.team.bonapp.domain.model.basic.full

import com.google.gson.annotations.SerializedName
import com.team.bonapp.domain.model.basic.Ingredients

data class FullRecipe(
    @SerializedName("uri") val uri : String,
    @SerializedName("label") val label : String,
    @SerializedName("image") val image : String,
    @SerializedName("source") val source : String,
    @SerializedName("url") val url : String,
    @SerializedName("shareAs") val shareAs : String,
    @SerializedName("yield") val yield : Int,
    @SerializedName("dietLabels") val dietLabels : List<String>,
    @SerializedName("healthLabels") val healthLabels : List<String>,
    @SerializedName("cautions") val cautions : List<String>,
    @SerializedName("ingredientLines") val ingredientLines : List<String>,
    @SerializedName("ingredients") val ingredients : List<Ingredients>,
    @SerializedName("calories") val calories : Double,
    @SerializedName("totalWeight") val totalWeight : Double,
    @SerializedName("totalTime") val totalTime : Int,
)