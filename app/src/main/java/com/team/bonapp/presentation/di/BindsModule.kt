package com.team.bonapp.presentation.di

import com.team.bonapp.data.repository.EdamamRepositoryImpl
import com.team.bonapp.domain.repository.EdamamRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface BindsModule {

    @Binds
    fun bindRepository(repositoryImpl: EdamamRepositoryImpl): EdamamRepository

}