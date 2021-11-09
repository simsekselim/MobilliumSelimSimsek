package com.mobillium.zonezero.di

import com.mobillium.zonezero.domain.repository.DoctorsRepository
import com.mobillium.zonezero.domain.usecase.DoctorsListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun provideDoctorsListUseCase(doctorsRepository: DoctorsRepository) = DoctorsListUseCase(doctorsRepository)

}