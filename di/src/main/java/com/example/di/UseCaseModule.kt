package com.example.di

import com.example.domain.repository.AccountRepository
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.MainRepository
import com.example.domain.repository.TokenRepository
import com.example.presentation.usecase.auth.login.InsertTokenUseCase
import com.example.presentation.usecase.auth.login.UserLoginUseCase
import com.example.presentation.usecase.auth.register.UserRegisterUseCase
import com.example.presentation.usecase.main.detail.GameDetailsUseCase
import com.example.presentation.usecase.main.home.PopularGamesUseCase
import com.example.presentation.usecase.main.home.RecommendedGamesUseCase
import com.example.presentation.usecase.main.profile.ClearDataUserUseCase
import com.example.presentation.usecase.main.profile.GetDataUserUseCase
import com.example.presentation.usecase.main.profile.UpdateDataUserUseCase
import com.example.presentation.usecase.main.splashscreen.CheckLoggedInUseCase
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
    ): InsertTokenUseCase {
        return InsertTokenUseCase(
            tokenRepository
        )
    }

    @Singleton
    @Provides
    fun provideUserLoginUseCase(
        authRepository: AuthRepository,
        insertTokenUseCase: InsertTokenUseCase
    ): UserLoginUseCase {
        return UserLoginUseCase(
            authRepository,
            insertTokenUseCase
        )
    }

    @Singleton
    @Provides
    fun provideUserRegisterUseCase(
        authRepository: AuthRepository
    ): UserRegisterUseCase {
        return UserRegisterUseCase(
            authRepository
        )
    }

    @Singleton
    @Provides
    fun provideCheckLoggedInUseCase(
        tokenRepository: TokenRepository
    ): CheckLoggedInUseCase {
        return CheckLoggedInUseCase(
            tokenRepository
        )
    }

    @Singleton
    @Provides
    fun provideGameDetailsUseCase(
        mainRepository: MainRepository
    ): GameDetailsUseCase {
        return GameDetailsUseCase(
            mainRepository
        )
    }

    @Singleton
    @Provides
    fun provideRecommendedGamesUseCase(
        mainRepository: MainRepository
    ): RecommendedGamesUseCase {
        return RecommendedGamesUseCase(
            mainRepository
        )
    }

    @Singleton
    @Provides
    fun providePopularGamesUseCase(
        mainRepository: MainRepository
    ): PopularGamesUseCase {
        return PopularGamesUseCase(
            mainRepository
        )
    }

    @Singleton
    @Provides
    fun provideUpdateDataUserUseCase(
        accountRepository: AccountRepository,
        tokenRepository: TokenRepository
    ): UpdateDataUserUseCase {
        return UpdateDataUserUseCase(
            accountRepository,
            tokenRepository
        )
    }

    @Singleton
    @Provides
    fun provideGetDataUserUseCase(
        accountRepository: AccountRepository,
        tokenRepository: TokenRepository
    ): GetDataUserUseCase {
        return GetDataUserUseCase(
            accountRepository,
            tokenRepository
        )
    }

    @Singleton
    @Provides
    fun provideClearDataUserUseCase(
        tokenRepository: TokenRepository
    ): ClearDataUserUseCase {
        return ClearDataUserUseCase(
            tokenRepository
        )
    }
}