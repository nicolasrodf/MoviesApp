package com.nicolasrf.moviesapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.nicolasrf.moviesapp.R
import com.nicolasrf.moviesapp.data.matcher.EmailMatcherImpl
import com.nicolasrf.moviesapp.data.remote.RemoteService
import com.nicolasrf.moviesapp.data.repository.AuthenticationRepositoryImpl
import com.nicolasrf.moviesapp.data.repository.MoviesRepositoryImpl
import com.nicolasrf.moviesapp.domain.matcher.EmailMatcher
import com.nicolasrf.moviesapp.domain.repository.AuthenticationRepository
import com.nicolasrf.moviesapp.domain.repository.MoviesRepository
import com.nicolasrf.moviesapp.usecases.LoginUseCases
import com.nicolasrf.moviesapp.usecases.LoginWithEmailUseCase
import com.nicolasrf.moviesapp.usecases.LogoutUseCase
import com.nicolasrf.moviesapp.usecases.ValidateEmailUseCase
import com.nicolasrf.moviesapp.usecases.ValidateLoggedInUseCase
import com.nicolasrf.moviesapp.usecases.ValidatePasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("movies_preferences", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAuthenticationRepository(sharedPreferences: SharedPreferences): AuthenticationRepository {
        return AuthenticationRepositoryImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(@ApiKey apiKey: String, remoteService: RemoteService): MoviesRepository {
        return MoviesRepositoryImpl(apiKey,remoteService)
    }

    @Provides
    @Singleton
    fun provideEmailMatcher(): EmailMatcher {
        return EmailMatcherImpl()
    }

    @Provides
    @Singleton
    fun provideLoginUseCases(
        repository: AuthenticationRepository,
        emailMatcher: EmailMatcher
    ): LoginUseCases {
        return LoginUseCases(
            loginWithEmailUseCase = LoginWithEmailUseCase(repository),
            validateEmailUseCase = ValidateEmailUseCase(emailMatcher),
            validatePasswordUseCase = ValidatePasswordUseCase()
        )
    }

    @Provides
    @Singleton
    fun provideLogoutUseCase(repository: AuthenticationRepository): LogoutUseCase {
        return LogoutUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideValidateLoggedInUseCase(repository: AuthenticationRepository): ValidateLoggedInUseCase {
        return ValidateLoggedInUseCase(repository)
    }

    @Provides
    @Singleton
    @ApiKey
    fun provideApiKey(app: Application): String = app.getString(R.string.api_key)

    @Provides
    @Singleton
    @ApiUrl
    fun provideApiUrl(): String = "https://api.themoviedb.org/3/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    @Provides
    @Singleton
    fun provideRemoteService(@ApiUrl apiUrl: String, okHttpClient: OkHttpClient): RemoteService {
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
}