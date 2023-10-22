package com.itcampos.mynewsappcompose.domain.usecases.news

import com.itcampos.mynewsappcompose.data.local.NewsDao
import com.itcampos.mynewsappcompose.domain.model.Article
import com.itcampos.mynewsappcompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}