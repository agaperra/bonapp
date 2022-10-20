package com.team.bonapp.domain.model.basic

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Edamam(
        @SerializedName("hits") var hits : ArrayList<Hits>
) : Serializable
