package com.example.composecleanapparchitecture.di

import com.example.composecleanapparchitecture.store.data.remote.ProductsApi
import com.example.composecleanapparchitecture.store.data.repository.ProductsRepositoryImpl
import com.example.composecleanapparchitecture.store.domain.repository.ProductRepository
import com.example.composecleanapparchitecture.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun providesProductsApi(): ProductsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductsApi::class.java)
    }

    // alt way of providing products repo without using Bind in Repository Module
//    @Provides
//    @Singleton
//    fun provideProductsRepository(impl: ProductsRepositoryImpl): ProductRepository {
//        return impl
//    }
}