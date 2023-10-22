package com.itcampos.mynewsappcompose.presentation.bookmark

import com.itcampos.mynewsappcompose.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
