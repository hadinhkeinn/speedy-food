package com.buiducha.speedyfood.ui.screens.settings.settings_screen

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LockReset
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.buiducha.speedyfood.R

enum class SettingItems(
    @StringRes val settingLabel: Int,
    val icon: ImageVector
) {
    EditProfile(
        settingLabel = R.string.edit_profile,
        icon = Icons.Default.Person
    ),
    ChangePassword(
        settingLabel = R.string.change_password,
        icon = Icons.Default.LockReset
    ),
    Logout(
        settingLabel = R.string.log_out,
        icon = Icons.Default.Logout
    ),
}