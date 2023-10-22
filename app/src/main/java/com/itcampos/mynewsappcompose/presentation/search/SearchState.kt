package com.itcampos.mynewsappcompose.presentation.search

import androidx.paging.PagingData
import com.itcampos.mynewsappcompose.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
) {
}