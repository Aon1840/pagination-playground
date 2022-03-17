package com.bearman.pagination_playground.di

import android.app.Application
import android.content.Context
import com.bearman.pagination_playground.MainApplication
import com.bearman.pagination_playground.data.room.dao.UserDao
import com.bearman.pagination_playground.data.room.database.UserDatabase
import com.bearman.pagination_playground.domain.executor.PostExecutionThread
import com.bearman.pagination_playground.domain.executor.ThreadExecutor
import com.bearman.pagination_playground.domain.repository.UserRepository
import com.bearman.pagination_playground.domain.usecase.AddUserUseCase
import com.bearman.pagination_playground.domain.usecase.GetAllUserUseCase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.internal.PrepareOp
import javax.inject.Singleton

@Module
class ApiModule {

    private val BASE_URL = "https://pokeapi.co/api/v2/"
    private val BASE_POKEMON_URL =
        "https://gist.githubusercontent.com/mrcsxsiq/b94dbe9ab67147b642baa9109ce16e44/raw/97811a5df2df7304b5bc4fbb9ee018a0339b8a38/"

    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideUserDatabase(context: Context): UserDatabase {
        return UserDatabase.getAppDatabase(context)
    }

    @Singleton
    @Provides
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao()
    }

    @Singleton
    @Provides
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepository(userDao)
    }

    @Singleton
    @Provides
    fun provideGetAllUserUseCase(
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread,
        userRepository: UserRepository
    ): GetAllUserUseCase {
        return GetAllUserUseCase(
            threadExecutor,
            postExecutionThread,
            userRepository
        )
    }

    @Singleton
    @Provides
    fun provideAddUserUseCase(
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread,
        userRepository: UserRepository
    ): AddUserUseCase {
        return AddUserUseCase(
            threadExecutor,
            postExecutionThread,
            userRepository
        )
    }

//    @Singleton
//    @Provides
//    fun provideCountriesApi(): PokeApi {
//        return Retrofit.Builder()
//            .baseUrl(BASE_POKEMON_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//            .create(PokeApi::class.java)
//    }
//
//    @Singleton
//    @Provides
//    fun providePokemonService(pokeApi: PokeApi): PokemonService {
//        return PokemonService(pokeApi)
//    }
//
//    @Singleton
//    @Provides
//    fun providePokemonRepository(pokemonService: PokemonService): PokemonRepository {
//        return PokemonRepository(pokemonService)
//    }
//
//    @Singleton
//    @Provides
//    fun provideListPokemonUseCase(
//        threadExecutor: ThreadExecutor,
//        postExecutionThread: PostExecutionThread,
//        repository: PokemonRepository
//    ): ListPokemonUseCase {
//        return ListPokemonUseCase(
//            threadExecutor,
//            postExecutionThread,
//            repository
//        )
//    }
//
//    @Singleton
//    @Provides
//    fun provideListPokemonDetailUseCase(
//        threadExecutor: ThreadExecutor,
//        postExecutionThread: PostExecutionThread,
//        repository: PokemonRepository
//    ): ListPokemonDetail {
//        return ListPokemonDetail(
//            threadExecutor,
//            postExecutionThread,
//            repository
//        )
//    }
}