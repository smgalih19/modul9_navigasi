package com.example.functionalcompose.di

import com.example.functionalcompose.networks.SetoranSampahApi
import com.example.functionalcompose.persistences.SetoranSampahDao
import com.example.functionalcompose.repositories.SetoranSampahRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideSetoranSampahRepository(
        api: SetoranSampahApi,
        dao: SetoranSampahDao
    ): SetoranSampahRepository{
        return SetoranSampahRepository(api, dao)
    }
}