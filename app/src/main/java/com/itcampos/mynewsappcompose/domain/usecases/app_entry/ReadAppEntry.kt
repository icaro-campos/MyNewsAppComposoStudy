package com.itcampos.mynewsappcompose.domain.usecases.app_entry

import com.itcampos.mynewsappcompose.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {
    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}