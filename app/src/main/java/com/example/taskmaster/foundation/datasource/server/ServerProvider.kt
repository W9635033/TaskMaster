package com.example.taskmaster.foundation.datasource.server

import com.example.taskmaster.model.Credential
import com.example.taskmaster.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

class ServerProvider @Inject constructor() {

    fun fetchCredential(): Flow<Credential> {
        return flow { emit(Credential(token = UUID.randomUUID().toString())) }
    }

    fun fetchUser(email: String, password: String): Flow<User> {
        return flow { emit(User(email)) }
    }

}
