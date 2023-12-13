package com.example.taskmaster.runtime.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.taskmaster.features.todo.scheduled.ui.ScheduledScreen
import com.example.taskmaster.features.todo.scheduled.ui.ScheduledViewModel

fun NavGraphBuilder.ScheduledNavHost(
    navController: NavHostController,
    backIcon: ImageVector
) {
    navigation(
        startDestination = ScheduledFlow.ScheduledScreen.route,
        route = ScheduledFlow.Root.route
    ) {
        composable(
            route = ScheduledFlow.ScheduledScreen.route,
            arguments = ScheduledFlow.ScheduledScreen.arguments
        ) {
            val viewModel = hiltViewModel<ScheduledViewModel>()
            ScheduledScreen(
                viewModel = viewModel,
                backIcon = backIcon,
                onClickBack = { navController.navigateUp() },
                onTaskItemClick = { taskId, listId -> navController.navigate(StepFlow.Root.route(taskId, listId)) }
            )
        }
    }
}
