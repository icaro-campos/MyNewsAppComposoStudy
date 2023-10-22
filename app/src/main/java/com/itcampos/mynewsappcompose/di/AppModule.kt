package com.itcampos.mynewsappcompose.di

import android.app.Application
import androidx.room.Room
import com.itcampos.mynewsappcompose.data.local.NewsDao
import com.itcampos.mynewsappcompose.data.local.NewsDatabase
import com.itcampos.mynewsappcompose.data.local.NewsTypeConverter
import com.itcampos.mynewsappcompose.data.manager.LocalUserManagerImpl
import com.itcampos.mynewsappcompose.data.remote.NewsApi
import com.itcampos.mynewsappcompose.data.repository.NewsRepositoryImpl
import com.itcampos.mynewsappcompose.domain.manager.LocalUserManager
import com.itcampos.mynewsappcompose.domain.repository.NewsRepository
import com.itcampos.mynewsappcompose.domain.usecases.app_entry.AppEntryUseCases
import com.itcampos.mynewsappcompose.domain.usecases.app_entry.ReadAppEntry
import com.itcampos.mynewsappcompose.domain.usecases.app_entry.SaveAppEntry
import com.itcampos.mynewsappcompose.domain.usecases.news.DeleteArticle
import com.itcampos.mynewsappcompose.domain.usecases.news.GetNews
import com.itcampos.mynewsappcompose.domain.usecases.news.NewsUseCases
import com.itcampos.mynewsappcompose.domain.usecases.news.SearchNews
import com.itcampos.mynewsappcompose.domain.usecases.news.SelectArticle
import com.itcampos.mynewsappcompose.domain.usecases.news.SelectArticles
import com.itcampos.mynewsappcompose.domain.usecases.news.UpsertArticle
import com.itcampos.mynewsappcompose.util.Constants.BASE_URL
import com.itcampos.mynewsappcompose.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(newsApi, newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application,
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao
}