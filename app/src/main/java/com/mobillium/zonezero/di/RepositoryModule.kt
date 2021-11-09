package com.mobillium.zonezero.di

import com.mobillium.zonezero.data.api.ApiService
import com.mobillium.zonezero.data.repository.DoctorRepositoryImpl
import com.mobillium.zonezero.data.source.LocalDataSource
import com.mobillium.zonezero.data.source.RemoteDataSource
import com.mobillium.zonezero.domain.repository.DoctorsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideLocalDataSource() : LocalDataSource = LocalDataSource()

    @Provides
    fun provideRemoteDataSource(apiService: ApiService) : RemoteDataSource = RemoteDataSource(apiService)

    @Provides
    fun provideRepository(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource) :
            DoctorsRepository = DoctorRepositoryImpl(localDataSource, remoteDataSource)
}