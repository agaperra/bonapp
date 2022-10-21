package com.team.bonapp.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager.widget.ViewPager
import com.team.bonapp.databinding.FragmentHomeBinding
import com.team.bonapp.data.db.MealValues
import com.team.bonapp.presentation.adapters.pager.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doInitialization()
    }

    private fun doInitialization() {
        setupViewPager(binding.viewpager)
        binding.slidingTabs.setupWithViewPager(binding.viewpager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)

        MealValues()["Dish type category"]?.forEach { category ->
            adapter.addFragment(PagerFragment(category), category)
            viewPager.adapter = adapter
        }

        var job: Job? = null
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                homeViewModel.setContentLoading()
                job?.cancel()
                job = MainScope().launch {
                    delay(600)
                    homeViewModel.currentFragmentId = position
                    homeViewModel.getContent()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}