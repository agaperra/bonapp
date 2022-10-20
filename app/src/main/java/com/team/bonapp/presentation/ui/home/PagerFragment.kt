package com.team.bonapp.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.team.bonapp.databinding.FragmentPagerBinding
import com.team.bonapp.domain.AppState
import com.team.bonapp.domain.model.basic.Recipe
import com.team.bonapp.presentation.adapters.MainContentListAdapter
import com.team.bonapp.presentation.ui.home.HomeViewModel
import com.team.bonapp.utils.launchWhenStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
@ExperimentalCoroutinesApi
class PagerFragment(private val dishType: String) : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()

    private val recipeAdapter by lazy {
        MainContentListAdapter()
    }

    private var _binding: FragmentPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (view.isVisible) {
            doInitialization()
        }
    }

    private fun doInitialization() {

        binding.pagerRV.adapter = recipeAdapter

        startObserve(homeViewModel.mainContent)
    }

    private fun setResult(result: AppState<List<Recipe>>) {
        when (result) {
            is AppState.Error -> {
                binding.progressBar.isVisible = false
//                Toast.makeText(requireContext(), result.data.toString(), Toast.LENGTH_SHORT).show()
            }
            is AppState.Loading -> {
                binding.pagerRV.isVisible = false
                binding.progressBar.isVisible = true
            }
            is AppState.Success -> {
                recipeAdapter.submitList(result.data)
                binding.pagerRV.isVisible = true
                binding.progressBar.isVisible = false
            }
        }
    }

    private fun startObserve(contentSource: StateFlow<AppState<List<Recipe>>>) {
        contentSource.onEach { result ->
            setResult(result)
        }.launchWhenStarted(lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}