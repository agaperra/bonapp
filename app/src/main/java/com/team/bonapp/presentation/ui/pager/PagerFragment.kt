package com.team.bonapp.presentation.ui.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.team.bonapp.BuildConfig
import com.team.bonapp.databinding.FragmentPagerBinding
import com.team.bonapp.domain.AppState
import com.team.bonapp.domain.model.basic.Recipe
import com.team.bonapp.presentation.adapters.MainContentListAdapter
import com.team.bonapp.utils.launchWhenStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import java.util.*


@AndroidEntryPoint
@ExperimentalCoroutinesApi
class PagerFragment(private val dishType: String) : Fragment() {

    private val pagerViewModel: PagerViewModel by activityViewModels()

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
        Toast.makeText(requireContext(), dishType, Toast.LENGTH_SHORT).show()
        doInitialization()
    }

    private fun doInitialization() {

        binding.pagerRV.adapter = recipeAdapter

        pagerViewModel.getContent(
            app_id = BuildConfig.app_id,
            app_key = BuildConfig.app_key,
            query = dishType.lowercase(Locale.ROOT),
            dishType = dishType
        )

        startObserve(pagerViewModel.mainContent)
    }

    private fun setResult(result: AppState<List<Recipe>>) {
        when (result) {
            is AppState.Error -> {
                binding.progressBar.isVisible = false
                Toast.makeText(requireContext(), result.data.toString(), Toast.LENGTH_SHORT).show()
            }
            is AppState.Loading -> {
                binding.progressBar.isVisible = true
            }
            is AppState.Success -> {
                recipeAdapter.submitList(result.data)
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