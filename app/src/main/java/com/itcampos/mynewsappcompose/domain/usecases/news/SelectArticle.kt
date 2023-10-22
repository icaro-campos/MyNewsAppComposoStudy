package com.itcampos.mynewsappcompose.domain.usecases.news

import com.itcampos.mynewsappcompose.data.local.NewsDao
import com.itcampos.mynewsappcompose.domain.model.Article
import com.itcampos.mynewsappcompose.domain.repository.NewsRepository

class SelectArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String): Article? {
        return newsRepository.selectArticle(url)
    }
}