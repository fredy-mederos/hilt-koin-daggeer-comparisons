package com.sample.feature_todo.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.sample.base_android.ActionLiveData
import com.sample.domain.login.LogoutUseCase
import com.sample.domain.todo.ToDoModel
import com.sample.feature_todo.domain.GetCurrentUserToDosUseCase
import kotlinx.coroutines.launch

class ToDosViewModel @ViewModelInject constructor(
    val getCurrentUserToDosUseCase: GetCurrentUserToDosUseCase,
    val logoutUseCase: LogoutUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val onLogout: LiveData<Unit> = ActionLiveData()

    val todos: LiveData<List<ToDoModel>> = MutableLiveData()

    init {
        viewModelScope.launch {
            (todos as MutableLiveData).value = getCurrentUserToDosUseCase()
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
            (onLogout as ActionLiveData).sendAction(Unit)
        }
    }
}