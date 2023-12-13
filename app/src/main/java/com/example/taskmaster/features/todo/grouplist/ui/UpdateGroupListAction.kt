package com.example.taskmaster.features.todo.grouplist.ui

import com.example.taskmaster.model.GroupIdWithList

sealed class UpdateGroupListAction {

    data class Change(val item: GroupIdWithList) : UpdateGroupListAction()
    object Submit : UpdateGroupListAction()

}
