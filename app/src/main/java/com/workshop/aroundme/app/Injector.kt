package com.workshop.aroundme.app

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.workshop.aroundme.data.repository.CategoryRepository
import com.workshop.aroundme.data.repository.PlaceRepository
import com.workshop.aroundme.data.repository.UserRepository
import com.workshop.aroundme.local.AppDatabase
import com.workshop.aroundme.local.datasource.PlaceLocalDataSource
import com.workshop.aroundme.local.datasource.UserLocalDataSource
import com.workshop.aroundme.remote.NetworkManager
import com.workshop.aroundme.remote.datasource.CategoryRemoteDataSource
import com.workshop.aroundme.remote.datasource.PlaceRemoteDataSource
import com.workshop.aroundme.remote.service.CategoryService
import com.workshop.aroundme.remote.service.PlaceService

object Injector {

    private val networkManager = NetworkManager()

    fun provideCategoryRepository(
        categoryRemoteDataSource: CategoryRemoteDataSource = provideCategoryRemoteDataSource()
    ): CategoryRepository {
        return CategoryRepository(categoryRemoteDataSource)
    }

    private fun provideCategoryRemoteDataSource(
        categoryService: CategoryService = provideCategoryService()
    ): CategoryRemoteDataSource {
        return CategoryRemoteDataSource(categoryService)
    }

    private fun provideCategoryService(): CategoryService {
        return CategoryService(networkManager)
    }

    fun providePlaceRepository(
        context: Context,
        placeLocalDataSource: PlaceLocalDataSource = providePlaceLocalDataSource(context),
        placeRemoteDataSource: PlaceRemoteDataSource = providePlaceRemoteDataSource()
    ): PlaceRepository {
        return PlaceRepository(placeLocalDataSource, placeRemoteDataSource)
    }

    private fun providePlaceLocalDataSource(context: Context): PlaceLocalDataSource {
        return PlaceLocalDataSource(provideAppDatabase(context).placeDao())
    }

    private fun providePlaceRemoteDataSource(
        placeService: PlaceService = providePlaceService()
    ): PlaceRemoteDataSource {
        return PlaceRemoteDataSource(placeService)
    }

    private fun providePlaceService(): PlaceService {
        return PlaceService(networkManager)
    }

    fun provideUserRepository(
        context: Context,
        userLocalDataSource: UserLocalDataSource = provideUserLocalDataSource(context)
    ): UserRepository {
        return UserRepository(userLocalDataSource)
    }

    private fun provideUserLocalDataSource(
        context: Context,
        defaultSharedPreferences: SharedPreferences = provideDefaultSharedPref(context)
    ): UserLocalDataSource {
        return UserLocalDataSource(defaultSharedPreferences)
    }

    private fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "db.data").build()
    }

    private fun provideDefaultSharedPref(context: Context): SharedPreferences {
        return context.getSharedPreferences("user.data", Context.MODE_PRIVATE)
    }
}
