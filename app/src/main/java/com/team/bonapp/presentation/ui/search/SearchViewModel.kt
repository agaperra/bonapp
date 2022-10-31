package com.team.bonapp.presentation.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team.bonapp.data.db.MealValues
import com.team.bonapp.domain.AppState
import com.team.bonapp.domain.ErrorState
import com.team.bonapp.domain.model.basic.Recipe
import com.team.bonapp.domain.use_case.GetFilteredContent
import com.team.bonapp.utils.network.ConnectionState
import com.team.bonapp.utils.network.NetworkStatusListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModel @Inject constructor(
    private val getFilteredContent: GetFilteredContent,
    private val networkStatusListener: NetworkStatusListener
): ViewModel(){


    private val _filteredContent = MutableStateFlow<AppState<List<Recipe>>>(AppState.Loading())
    var filteredContent = _filteredContent.asStateFlow()

    private val _filteredContentLoading = MutableStateFlow(true)
    var filteredContentLoading = _filteredContentLoading.asStateFlow()


    init {
        networkStatusListener.networkStatus.onEach { status ->
            when (status) {
                ConnectionState.Available -> {
                    setContentLoading()
                }
                ConnectionState.Unavailable -> {
                    if (_filteredContent.value.data != null) _filteredContent.value =
                        AppState.Error(error = ErrorState.NO_INTERNET_CONNECTION)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getContent(args: SearchResultFragmentArgs) {
        getFilteredContent(
            query = args.q,
            diet = args.diet,
            dishType = args.dishType,
            health = args.health,
            cuisineType = args.cuisineType,
            mealType = args.mealType
        ).onEach { result ->
            when (result) {
                is AppState.Success -> {
                    _filteredContent.value = result
                    _filteredContentLoading.value = false
                }
                is AppState.Loading -> {
                    _filteredContentLoading.value = true
                }
                is AppState.Error -> {
                    _filteredContentLoading.value = false
                    _filteredContent.value = result
                    Timber.e(result.message?.name)
                }
            }

        }.launchIn(viewModelScope)
    }

    public fun setContentLoading() {
        _filteredContent.value = AppState.Loading()
        _filteredContentLoading.value = true
    }
}