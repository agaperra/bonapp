package com.team.bonapp.presentation.adapters

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team.bonapp.R
import com.team.bonapp.databinding.RecipeItemBinding
import com.team.bonapp.domain.model.basic.Recipe
import com.team.bonapp.presentation.adapters.diffutil.RecipeDiffUtil
import com.team.bonapp.utils.setImage
import jp.wasabeef.blurry.Blurry
import java.util.*


class MainContentListAdapter() :
    ListAdapter<Recipe, MainContentListAdapter.MainContentListViewHolder>(RecipeDiffUtil()) {

    inner class MainContentListViewHolder(private val binding: RecipeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(itemPosition: Int) {

            val recipe = getItem(itemPosition)

            binding.circleImageView.setImage(recipe.image)

            // TODO сделать неормально отображение фона без задержек

//            val bitmap = Blurry.with(getContext())
//                .radius(10)
//                .sampling(8)
//                .capture(binding.circleImageView).get()
//            binding.backgroundBlur.setImageDrawable(BitmapDrawable(getContext()?.resources, bitmap))

            Blurry.with(getContext())
                .radius(25)
                .sampling(2)
                .capture(binding.circleImageView)
                .getAsync {
                    binding.backgroundBlur.setImageDrawable(
                        BitmapDrawable(
                            getContext()?.resources,
                            it
                        )
                    )
                }
            itemView.invalidate()

            binding.name.text = recipe.label.uppercase(Locale.ROOT)
            binding.kal.text = recipe.calories.toInt().toString()
            binding.time.text =
                if (recipe.totalTime == 0) 1.toString() else recipe.totalTime.toString()
            binding.weight.text = recipe.totalWeight.toInt().toString()

        }

        private fun getContext(): Context? {
            return itemView.context
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