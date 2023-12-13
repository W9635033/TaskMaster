package com.example.taskmaster.runtime.navigation

import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import com.example.taskmaster.features.localized.setting.ui.LanguageScreen
import com.example.taskmaster.features.localized.setting.ui.LocalizedSettingViewModel
import com.example.taskmaster.features.setting.ui.SettingScreen
import com.example.taskmaster.features.setting.ui.SettingViewModel
import com.example.taskmaster.features.theme.ui.ThemeScreen
import com.example.taskmaster.features.theme.ui.ThemeViewModel

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.SettingNavHost(
    navController: NavHostController,
    bottomSheetConfig: MutableState<MainBottomSheetConfig>
) {
    navigation(startDestination = SettingFlow.Setting.route, route = SettingFlow.Root.route) {
        bottomSheet(SettingFlow.Setting.route) {
            val viewModel = hiltViewModel<SettingViewModel>()
            bottomSheetConfig.value = DefaultMainBottomSheetConfig
            SettingScreen(
                viewModel = viewModel,
                onClickLogout = { navController.navigate(SettingFlow.Logout.route) },
                onClickLanguage = { navController.navigate(SettingFlow.Language.route) },
                onClickTheme = { navController.navigate(SettingFlow.Theme.route) }
            )
        }
        bottomSheet(SettingFlow.Theme.route) {
            val viewModel = hiltViewModel<ThemeViewModel>()
            bottomSheetConfig.value = DefaultMainBottomSheetConfig
            ThemeScreen(
                viewModel = viewModel,
                onClickBack = { navController.navigateUp() }
            )
        }
        bottomSheet(SettingFlow.Logout.route) {
            bottomSheetConfig.value = DefaultMainBottomSheetConfig
        }
        bottomSheet(SettingFlow.Language.route) {
            val viewModel = hiltViewModel<LocalizedSettingViewModel>()
            bottomSheetConfig.value = DefaultMainBottomSheetConfig
            LanguageScreen(
                viewModel = viewModel,
                onClickBack = { navController.navigateUp() }
            )
        }
    }
}
