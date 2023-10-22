package com.itcampos.mynewsappcompose.domain.usecases.app_entry

import com.itcampos.mynewsappcompose.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}