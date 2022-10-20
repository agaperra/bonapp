package com.team.bonapp.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import com.team.bonapp.R
import com.team.bonapp.databinding.FragmentSearchBinding
import com.team.bonapp.db.FilterValues
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
            // Добавить стиль в конструктор
            val title = TextView(context)
            title.text = key
            binding.llFilter.addView(title)

            val chipGroup = ChipGroup(context, null, R.style.Colors_Widget_MaterialComponents_Chip_Choice)
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
                chip.text = category
                chip.elevation = 2f
                chip.isCheckable = true
                chip.isClickable = true
                chip.setChipDrawable(chipDrawable)

                chipGroup.addView(chip)
            }

            binding.llFilter.addView(chipGroup)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}