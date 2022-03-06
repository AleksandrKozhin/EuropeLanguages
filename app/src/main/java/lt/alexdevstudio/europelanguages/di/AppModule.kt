package lt.alexdevstudio.europelanguages.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lt.alexdevstudio.europelanguages.data.network.CountryApi
import lt.alexdevstudio.europelanguages.domain.repository.CountryRepository
import lt.alexdevstudio.europelanguages.domain.repository.CountryRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://restcountries.com"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideCountryApi(retrofit: Retrofit): CountryApi =
        retrofit.create(CountryApi::class.java)

    @Provides
    @Singleton
    fun provideCountryRepository(api: CountryApi): CountryRepository =
        CountryRepositoryImpl(api)
}