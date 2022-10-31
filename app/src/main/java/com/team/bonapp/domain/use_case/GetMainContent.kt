package com.team.bonapp.domain.use_case

import com.team.bonapp.BuildConfig
import com.team.bonapp.data.mapper.toRecipe
import com.team.bonapp.domain.AppState
import com.team.bonapp.domain.ErrorState
import com.team.bonapp.domain.repository.EdamamRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class GetMainContent @Inject constructor(private val edamamRepository: EdamamRepository) {

    operator fun invoke(
        app_id: String = BuildConfig.app_id,
        app_key: String = BuildConfig.app_key,
        query: String,
        dishType: String,
    ) = flow {
        emit(AppState.Loading())
        try {
            val response =
                edamamRepository.mainContent(
                    app_id = app_id,
                    app_key = app_key,
                    query = query,
                    dishType = dishType
                )
            emit(AppState.Success(data = response))
        } catch (exception: HttpException) {
            if (exception.code() != 400)
                emit(AppState.Error(error = ErrorState.NO_INTERNET_CONNECTION))
            Timber.e(exception)
        } catch (exception: Exception) {
            emit(AppState.Error(error = ErrorState.NO_INTERNET_CONNECTION))
            Timber.e(exception)
        }
    }
}