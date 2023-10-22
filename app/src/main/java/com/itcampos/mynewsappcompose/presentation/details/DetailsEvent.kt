package com.itcampos.mynewsappcompose.presentation.details

import com.itcampos.mynewsappcompose.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(
        val article: Article
    ) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()
}