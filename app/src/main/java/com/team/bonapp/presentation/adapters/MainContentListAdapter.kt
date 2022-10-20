package com.team.bonapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team.bonapp.R
import com.team.bonapp.databinding.RecipeItemBinding
import com.team.bonapp.domain.model.basic.Recipe
import com.team.bonapp.presentation.adapters.diffutil.RecipeDiffUtil
import com.team.bonapp.utils.setImage

class MainContentListAdapter() :
    ListAdapter<Recipe, MainContentListAdapter.MainContentListViewHolder>(RecipeDiffUtil()) {

    inner class MainContentListViewHolder(private val binding: RecipeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(itemPosition: Int) {

            val recipe = getItem(itemPosition)

            binding.circleImageView.setImage(recipe.image)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MainContentListViewHolder(
            RecipeItemBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.recipe_item,
                    parent,
                    false
                )
            )
        )

    override fun onBindViewHolder(holder: MainContentListViewHolder, position: Int) =
        holder.bind(itemPosition = position)

}