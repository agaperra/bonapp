package com.team.bonapp.presentation.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.team.bonapp.databinding.FragmentSearchResultBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class SearchResultFragment : Fragment() {
    private var _binding: FragmentSearchResultBinding? = null
    private val binding get() = _binding!!

    val args: SearchResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchResultBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvSearchResult.text =
            "Query: ${args.q}" +
            "\nDiet: ${Arrays.toString(args.diet)}" +
            "\nHealth: ${Arrays.toString(args.health)}" +
            "\nCusine: ${Arrays.toString(args.cuisineType)}" +
            "\nMeal: ${Arrays.toString(args.mealType)}" +
            "\nDish: ${Arrays.toString(args.dishType)}"

    }

}