package com.example.challengechapter6.di

import com.example.domain.AccountRepository
import com.example.domain.AuthRepository
import com.example.domain.MainRepository
import com.example.domain.TokenRepository
import com.example.challengechapter6.presentation.usecase.auth.login.InsertTokenUseCase
import com.example.challengechapter6.presentation.usecase.auth.login.UserLoginUseCase
import com.example.challengechapter6.presentation.usecase.auth.register.UserRegisterUseCase
import com.example.challengechapter6.presentation.usecase.main.detail.GameDetailsUseCase
import com.example.challengechapter6.presentation.usecase.main.home.PopularGamesUseCase
import com.example.challengechapter6.presentation.usecase.main.home.RecommendedGamesUseCase
import com.example.challengechapter6.presentation.usecase.main.profile.ClearDataUserUseCase
import com.example.challengechapter6.presentation.usecase.main.profile.GetDataUserUseCase
import com.example.challengechapter6.presentation.usecase.main.profile.UpdateDataUserUseCase
import com.example.challengechapter6.presentation.usecase.main.splashscreen.CheckLoggedInUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideInsertTokenUseCase(
        tokenRepository: TokenRepository
    ): com.example.challengechapter6.presentation.usecase.auth.login.InsertTokenUseCase {
        return com.example.challengechapter6.presentation.usecase.auth.login.InsertTokenUseCase(
            tokenRepository
        )
    }

    @Singleton
    @Provides
    fun provideUserLoginUseCase(
        authRepository: AuthRepository,
        insertTokenUseCase: com.example.challengechapter6.presentation.usecase.auth.login.InsertTokenUseCase
    ): com.example.challengechapter6.presentation.usecase.auth.login.UserLoginUseCase {
        return com.example.challengechapter6.presentation.usecase.auth.login.UserLoginUseCase(
            authRepository,
            insertTokenUseCase
        )
    }

    @Singleton
    @Provides
    fun provideUserRegisterUseCase(
        authRepository: AuthRepository
    ): com.example.challengechapter6.presentation.usecase.auth.register.UserRegisterUseCase {
        return com.example.challengechapter6.presentation.usecase.auth.register.UserRegisterUseCase(
            authRepository
        )
    }

    @Singleton
    @Provides
    fun provideCheckLoggedInUseCase(
        tokenRepository: TokenRepository
    ): com.example.challengechapter6.presentation.usecase.main.splashscreen.CheckLoggedInUseCase {
        return com.example.challengechapter6.presentation.usecase.main.splashscreen.CheckLoggedInUseCase(
            tokenRepository
        )
    }

    @Singleton
    @Provides
    fun provideGameDetailsUseCase(
        mainRepository: MainRepository
    ): com.example.challengechapter6.presentation.usecase.main.detail.GameDetailsUseCase {
        return com.example.challengechapter6.presentation.usecase.main.detail.GameDetailsUseCase(
            mainRepository
        )
    }

    @Singleton
    @Provides
    fun provideRecommendedGamesUseCase(
        mainRepository: MainRepository
    ): com.example.challengechapter6.presentation.usecase.main.home.RecommendedGamesUseCase {
        return com.example.challengechapter6.presentation.usecase.main.home.RecommendedGamesUseCase(
            mainRepository
        )
    }

    @Singleton
    @Provides
    fun providePopularGamesUseCase(
        mainRepository: MainRepository
    ): com.example.challengechapter6.presentation.usecase.main.home.PopularGamesUseCase {
        return com.example.challengechapter6.presentation.usecase.main.home.PopularGamesUseCase(
            mainRepository
        )
    }

    @Singleton
    @Provides
    fun provideUpdateDataUserUseCase(
        accountRepository: AccountRepository,
        tokenRepository: TokenRepository
    ): com.example.challengechapter6.presentation.usecase.main.profile.UpdateDataUserUseCase {
        return com.example.challengechapter6.presentation.usecase.main.profile.UpdateDataUserUseCase(
            accountRepository,
            tokenRepository
        )
    }

    @Singleton
    @Provides
    fun provideGetDataUserUseCase(
        accountRepository: AccountRepository,
        tokenRepository: TokenRepository
    ): com.example.challengechapter6.presentation.usecase.main.profile.GetDataUserUseCase {
        return com.example.challengechapter6.presentation.usecase.main.profile.GetDataUserUseCase(
            accountRepository,
            tokenRepository
        )
    }

    @Singleton
    @Provides
    fun provideClearDataUserUseCase(
        tokenRepository: TokenRepository
    ): com.example.challengechapter6.presentation.usecase.main.profile.ClearDataUserUseCase {
        return com.example.challengechapter6.presentation.usecase.main.profile.ClearDataUserUseCase(
            tokenRepository
        )
    }
}