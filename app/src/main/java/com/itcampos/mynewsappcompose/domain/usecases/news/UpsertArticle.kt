package com.itcampos.mynewsappcompose.domain.usecases.news

import com.itcampos.mynewsappcompose.data.local.NewsDao
import com.itcampos.mynewsappcompose.domain.model.Article
import com.itcampos.mynewsappcompose.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article) {
        newsRepository.upsertArticle(article)
    }
}