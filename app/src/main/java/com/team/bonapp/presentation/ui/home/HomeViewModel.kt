package com.team.bonapp.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team.bonapp.data.db.MealValues
import com.team.bonapp.domain.AppState
import com.team.bonapp.domain.ErrorState
import com.team.bonapp.domain.model.basic.Recipe
import com.team.bonapp.domain.use_case.GetMainContent
import com.team.bonapp.utils.network.ConnectionState
import com.team.bonapp.utils.network.NetworkStatusListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class HomeViewModel @Inject constructor(
    private val getMainContent: GetMainContent,
    private val networkStatusListener: NetworkStatusListener
) : ViewModel() {


    private val _mainContent = MutableStateFlow<AppState<List<Recipe>>>(AppState.Loading())
    var mainContent = _mainContent.asStateFlow()

    private val _mainContentLoading = MutableStateFlow(true)
    var mainContentLoading = _mainContentLoading.asStateFlow()

    private val categories = MealValues["Dish type category"]!!
    var currentFragmentId = 0
    lateinit var dishType: String


    init {
        networkStatusListener.networkStatus.onEach { status ->
            when (status) {
                ConnectionState.Available -> {
                    getContent()
                }
                ConnectionState.Unavailable -> {
                    if (_mainContent.value.data != null) _mainContent.value =
                        AppState.Error(error = ErrorState.NO_INTERNET_CONNECTION)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getContent() {
        dishType = categories[currentFragmentId]
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

    public fun setContentLoading() {
        _mainContent.value = AppState.Loading()
        _mainContentLoading.value = true
    }
}