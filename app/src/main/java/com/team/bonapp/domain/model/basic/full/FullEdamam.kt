package com.team.bonapp.domain.model.basic.full

import com.google.gson.annotations.SerializedName

data class FullEdamam(
    @SerializedName("q") val q : String,
    @SerializedName("from") val from : Int,
    @SerializedName("to") val to : Int,
    @SerializedName("more") val more : Boolean,
    @SerializedName("count") val count : Int,
    @SerializedName("hits") val hits : List<FullHits>
)
