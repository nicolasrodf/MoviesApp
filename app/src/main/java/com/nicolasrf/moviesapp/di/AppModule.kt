package com.nicolasrf.moviesapp.di

import com.nicolasrf.moviesapp.data.matcher.EmailMatcherImpl
import com.nicolasrf.moviesapp.data.repository.AuthenticationRepositoryImpl
import com.nicolasrf.moviesapp.domain.matcher.EmailMatcher
import com.nicolasrf.moviesapp.domain.repository.AuthenticationRepository
import com.nicolasrf.moviesapp.usecases.LoginUseCases
import com.nicolasrf.moviesapp.usecases.LoginWithEmailUseCase
import com.nicolasrf.moviesapp.usecases.LogoutUseCase
import com.nicolasrf.moviesapp.usecases.ValidateEmailUseCase
import com.nicolasrf.moviesapp.usecases.ValidatePasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthenticationRepository(): AuthenticationRepository {
        return AuthenticationRepositoryImpl()
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
}