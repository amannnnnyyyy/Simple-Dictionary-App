package com.example.simpledictionary.di

import com.example.simpledictionary.data.repository.WordDetailRepositoryImpl
import com.example.simpledictionary.domain.repository.WordDetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WordDetailRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWordDetailRepository(wordDetailRepositoryImpl: WordDetailRepositoryImpl): WordDetailRepository
}