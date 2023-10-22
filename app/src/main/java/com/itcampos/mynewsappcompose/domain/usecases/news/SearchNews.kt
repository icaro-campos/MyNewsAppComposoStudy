package com.itcampos.mynewsappcompose.domain.usecases.news

import androidx.paging.PagingData
import com.itcampos.mynewsappcompose.domain.model.Article
import com.itcampos.mynewsappcompose.domain.repository.NewsRepository
import com.itcampos.mynewsappcompose.presentation.search.SearchEvent
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(searchQuery = searchQuery,sources = sources)
    }

}