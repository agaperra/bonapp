package com.team.bonapp.presentation.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.team.bonapp.databinding.FragmentSearchResultBinding
import com.team.bonapp.domain.AppState
import com.team.bonapp.domain.model.basic.Recipe
import com.team.bonapp.presentation.adapters.MainContentListAdapter
import com.team.bonapp.utils.launchWhenStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*



@AndroidEntryPoint
class SearchResultFragment : Fragment() {

    private val searchViewModel: SearchViewModel by activityViewModels()

    private val recipeAdapter by lazy {
        MainContentListAdapter()
    }

    private var _binding: FragmentSearchResultBinding? = null
    private val binding get() = _binding!!

    private val args: SearchResultFragmentArgs by navArgs()

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

        binding.rvSearch.adapter = recipeAdapter

        searchViewModel.getContent(args)
        startObserve(searchViewModel.filteredContent)

    }

    private fun setResult(result: AppState<List<Recipe>>) {
        when (result) {
            is AppState.Error -> {
                binding.pbSearchResult.isVisible = false
            }
            is AppState.Loading -> {
                binding.rvSearch.isVisible = false
                binding.pbSearchResult.isVisible = true
            }
            is AppState.Success -> {
                recipeAdapter.submitList(result.data)
                binding.rvSearch.isVisible = true
                binding.pbSearchResult.isVisible = false
            }
        }
    }

    private fun startObserve(contentSource: StateFlow<AppState<List<Recipe>>>) {
        contentSource.onEach { result ->
            setResult(result)
        }.launchIn(lifecycleScope)
    }

}