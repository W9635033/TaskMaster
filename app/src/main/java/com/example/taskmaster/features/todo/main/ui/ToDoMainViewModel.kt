package com.example.taskmaster.features.todo.main.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.RectangleShape
import androidx.lifecycle.viewModelScope
import com.wisnu.foundation.coreviewmodel.StatefulViewModel
import com.example.taskmaster.features.todo.main.data.IToDoMainEnvironment
import com.example.taskmaster.foundation.datasource.local.model.ToDoGroupDb
import com.example.taskmaster.foundation.theme.MediumRadius
import com.example.taskmaster.model.ToDoGroup
import com.example.taskmaster.model.ToDoList
import com.example.taskmaster.model.ToDoStatus
import com.example.taskmaster.runtime.navigation.ARG_LIST_ID
import com.example.taskmaster.runtime.navigation.AllFlow
import com.example.taskmaster.runtime.navigation.ListDetailFlow
import com.example.taskmaster.runtime.navigation.MainFlow
import com.example.taskmaster.runtime.navigation.ScheduledFlow
import com.example.taskmaster.runtime.navigation.ScheduledTodayFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoMainViewModel @Inject constructor(todoMainEnvironment: IToDoMainEnvironment) :
    StatefulViewModel<ToDoMainState, Unit, ToDoMainAction, IToDoMainEnvironment>(ToDoMainState(), todoMainEnvironment) {

    init {
        initToDo()
        initOverallCount()
    }

    override fun dispatch(action: ToDoMainAction) {
        when (action) {
            is ToDoMainAction.DeleteList -> {
                viewModelScope.launch {
                    environment.deleteList(action.itemListType.list)
                }
            }
            is ToDoMainAction.NavBackStackEntryChanged -> {
                viewModelScope.launch {
                    when (action.route) {
                        ListDetailFlow.ListDetailScreen.route -> {
                            val listId = action.arguments?.getString(ARG_LIST_ID)
                            if (listId.isNullOrBlank()) {
                                setState { copy(selectedItemState = SelectedItemState.Empty) }
                            } else {
                                setState { copy(selectedItemState = SelectedItemState.List(listId)) }
                            }
                        }
                        AllFlow.AllScreen.route -> {
                            setState { copy(selectedItemState = SelectedItemState.AllTask) }
                        }
                        ScheduledTodayFlow.ScheduledTodayScreen.route -> {
                            setState { copy(selectedItemState = SelectedItemState.ScheduledTodayTask) }
                        }
                        ScheduledFlow.ScheduledScreen.route -> {
                            setState { copy(selectedItemState = SelectedItemState.ScheduledTask) }
                        }
                        MainFlow.RootEmpty.route -> {
                            setState { copy(selectedItemState = SelectedItemState.Empty) }
                        }
                    }
                }
            }
        }
    }

    private fun initToDo() {
        viewModelScope.launch {
            environment.getGroup()
                .collect {
                    setState {
                        copy(
                            data = it,
                            currentDate = environment.dateTimeProvider.now().dayOfMonth.toString()
                        )
                    }
                }
        }
    }

    private fun initOverallCount() {
        viewModelScope.launch {
            environment.getOverallCount()
                .collect {
                    setState {
                        copy(
                            allTaskCount = it.allTaskCount.toString(),
                            scheduledTodayTaskCount = it.scheduledTodayTaskCount.toString(),
                            scheduledTaskCount = it.scheduledTaskCount.toString(),
                        )
                    }
                }
        }
    }

}

fun ItemMainState.ItemListType.cellShape() = when (this) {
    is ItemMainState.ItemListType.First -> {
        RoundedCornerShape(
            topStart = MediumRadius,
            topEnd = MediumRadius
        )
    }
    is ItemMainState.ItemListType.Last -> {
        RoundedCornerShape(
            bottomStart = MediumRadius,
            bottomEnd = MediumRadius
        )
    }
    is ItemMainState.ItemListType.Middle -> {
        RectangleShape
    }
    is ItemMainState.ItemListType.Single -> {
        RoundedCornerShape(size = MediumRadius)
    }
}

fun List<ToDoGroup>.toItemGroup(selectedItemState: SelectedItemState): List<ItemMainState> {
    val data = mutableListOf<ItemMainState>()

    forEach {
        if (it.id != ToDoGroupDb.DEFAULT_ID) {
            data.add(ItemMainState.ItemGroup(it))
        }
        data.addAll(it.lists.toItemListMainState(selectedItemState))
    }

    return data
}

private fun List<ToDoList>.toItemListMainState(selectedItemState: SelectedItemState): List<ItemMainState.ItemListType> {
    return mapIndexed { index, list ->
        val selected = selectedItemState is SelectedItemState.List && selectedItemState.listId == list.id
        if (size == 1) {
            ItemMainState.ItemListType.Single(
                list = list,
                selected = selected
            )
        } else {
            when (index) {
                0 -> ItemMainState.ItemListType.First(
                    list = list,
                    selected = selected
                )
                lastIndex -> ItemMainState.ItemListType.Last(
                    list = list,
                    selected = selected
                )
                else -> ItemMainState.ItemListType.Middle(
                    list = list,
                    selected = selected
                )
            }
        }
    }
}

fun ToDoList.totalTask() = tasks.filter { it.status == ToDoStatus.IN_PROGRESS }.size
