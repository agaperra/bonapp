package com.team.bonapp.presentation.ui.pager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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


    fun getContent(
        app_id: String,
        app_key: String,
        query: String,
        dishType: String
    ) {
        networkStatusListener.networkStatus.onEach { status ->
            when (status) {
                ConnectionState.Available -> {
                    getMainContent(app_id = app_id, app_key = app_key, query = query, dishType = dishType)
                }
                ConnectionState.Unavailable -> {
                    if (_mainContent.value.data != null) _mainContent.value =
                        AppState.Error(error = ErrorState.NO_INTERNET_CONNECTION)
                }
            }
        }.launchIn(viewModelScope)
    }
}