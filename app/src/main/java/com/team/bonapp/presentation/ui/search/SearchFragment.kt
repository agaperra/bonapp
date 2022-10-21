package com.team.bonapp.presentation.ui.search

import android.graphics.Typeface
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.forEach
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import com.team.bonapp.R
import com.team.bonapp.databinding.FragmentSearchBinding
import com.team.bonapp.data.db.FilterValues
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private var recipeBundle = Bundle()

//    private val recipeParameters = RecipeParameters()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createChips()

        binding.btnSearch.setOnClickListener(onButtonClickListener())
        binding.ibSearch.setOnClickListener(onButtonClickListener())

    }

    private fun createChips() {
        FilterValues.forEach { (key, value) ->
            val title = TextView(context)
            title.apply {
                text = key
                textSize = 20f
                setTypeface(typeface, Typeface.BOLD)
                setTextColor(ContextCompat.getColor(context, R.color.pale_olive))
                setPadding(8, 8, 0, 0)
            }

            binding.llFilter.addView(title)

            val chipGroup =
                ChipGroup(context)
            chipGroup.isSingleSelection = false
            chipGroup.isEnabled = true

            value.forEach { category ->
                val chip = Chip(context)
                val chipDrawable = ChipDrawable.createFromAttributes(
                    requireContext(),
                    null,
                    0,
                    R.style.Colors_Widget_MaterialComponents_Chip_Choice
                )

                chip.apply {
                    text = category
                    elevation = 2f
                    isCheckable = true
                    isClickable = true
                    textSize = 16f
                    setChipDrawable(chipDrawable)
                    setEnsureMinTouchTargetSize(false)
                }
                chipGroup.addView(chip)
            }

            chipGroup.chipSpacingVertical = 56
            chipGroup.chipSpacingHorizontal = 56

            binding.llFilter.addView(chipGroup)

            chipGroup.layoutParams.width = ChipGroup.LayoutParams.WRAP_CONTENT
            chipGroup.layoutParams.height = ChipGroup.LayoutParams.WRAP_CONTENT

            chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
                val propName = title.text.toString().filterNot { it == ' ' }.replaceFirstChar { it.lowercase() }
                val checkedChipsSet = mutableSetOf<String>()
                Toast.makeText(context, "Checked property is $propName", Toast.LENGTH_SHORT).show()

                group.forEach { checkedChip ->
                    if (checkedChip.id in checkedIds) {
                        checkedChipsSet.add((checkedChip as Chip).text.toString())
                    }
                }

                recipeBundle.putStringArray(propName, checkedChipsSet.toTypedArray())
//                Toast.makeText(context, "Checked chips in group are $checkedChipsSet", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun onButtonClickListener() = View.OnClickListener {

        Toast.makeText(context, "Button is clicked!", Toast.LENGTH_SHORT).show()
        if (binding.etSearch.text.isNotEmpty()) {
            recipeBundle.putString("q", binding.etSearch.text.toString())
        } else {
            recipeBundle.putString("q", "recipe")
        }

        findNavController().navigate(
            R.id.action_navigation_search_to_navigation_search_result,
            recipeBundle
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}