package com.team.bonapp.presentation.ui.pager

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team.bonapp.BuildConfig
import com.team.bonapp.data.db.MealValues
import com.team.bonapp.domain.AppState
import com.team.bonapp.domain.ErrorState
import com.team.bonapp.domain.model.basic.Recipe
import com.team.bonapp.domain.use_case.GetMainContent
import com.team.bonapp.utils.network.ConnectionState
import com.team.bonapp.utils.network.NetworkStatusListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class PagerViewModel @Inject constructor(
    private val getMainContent: GetMainContent,
    private val networkStatusListener: NetworkStatusListener
) : ViewModel() {


    private val _mainContent = MutableStateFlow<AppState<List<Recipe>>>(AppState.Loading())
    var mainContent = _mainContent.asStateFlow()

    private val _mainContentLoading = MutableStateFlow(true)
    val mainContentLoading = _mainContentLoading.asStateFlow()

    private val currentFragment = MealValues()["Dish type category"]!![0]

    init {
        networkStatusListener.networkStatus.onEach { status ->
            when (status) {
                ConnectionState.Available -> {
                    getContent(dishType = currentFragment)
                }
                ConnectionState.Unavailable -> {
                    if (_mainContent.value.data != null) _mainContent.value =
                        AppState.Error(error = ErrorState.NO_INTERNET_CONNECTION)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getContent(dishType: String) {
        getMainContent(query = dishType.lowercase(), dishType = dishType).onEach { result ->
            when (result) {
                is AppState.Success -> {
                    _mainContent.value = result
                    _mainContentLoading.value = false
                }
                is AppState.Loading -> {
                    _mainContentLoading.value = true
                }
                is AppState.Error -> {
                    _mainContentLoading.value = false
                    _mainContent.value = result
                    Timber.e(result.message?.name)
                }
            }

        }.launchIn(viewModelScope)
    }
}