package com.team.bonapp.presentation.adapters.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.team.bonapp.domain.model.basic.Recipe

class RecipeDiffUtil : DiffUtil.ItemCallback<Recipe>() {

    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe) =
        oldItem.label== newItem.label

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe) =
        oldItem == newItem

}