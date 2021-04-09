package com.ac03.covid.di

import android.content.Context
import androidx.room.Room
import com.ac03.covid.data.database.CountryDao
import com.ac03.covid.data.database.CovidDatabase
import com.ac03.covid.data.database.RoomDataSource
import com.ac03.covid.data.repository.CovidRepository
import com.ac03.covid.data.server.CovidRemoteDataSource
import com.ac03.covid.usecases.GetSummaryData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            CovidDatabase::class.java,
            "covid-db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideCountryDao(db: CovidDatabase) = db.countryDao()

    @Provides
    fun provideLocalDataSource(dao: CountryDao) = RoomDataSource(dao)

    @Provides
    fun provideRemoteDataSource() = CovidRemoteDataSource()

    @Provides
    fun provideCovidRepository(localDataSource: RoomDataSource, remoteDataSource: CovidRemoteDataSource) = CovidRepository(localDataSource, remoteDataSource)

    @Provides
    fun provideGetSummary(covidRepository: CovidRepository) = GetSummaryData(covidRepository)
}