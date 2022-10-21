package com.team.bonapp.presentation.ui.search

import android.app.ActionBar
import android.graphics.Typeface
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.content.ContextCompat
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
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

        doInitialization()
    }

    private fun doInitialization(){

        binding.ibSearch.setOnClickListener {
            Toast.makeText(context, "Search button clicked!", Toast.LENGTH_SHORT).show()
        }

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
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}