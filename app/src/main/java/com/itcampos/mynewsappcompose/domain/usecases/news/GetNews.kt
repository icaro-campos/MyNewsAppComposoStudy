package com.itcampos.mynewsappcompose.domain.usecases.news

import androidx.paging.PagingData
import com.itcampos.mynewsappcompose.domain.model.Article
import com.itcampos.mynewsappcompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}