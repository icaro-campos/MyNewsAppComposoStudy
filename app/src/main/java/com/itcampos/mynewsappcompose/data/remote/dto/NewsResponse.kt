package com.itcampos.mynewsappcompose.data.remote.dto

import com.itcampos.mynewsappcompose.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)