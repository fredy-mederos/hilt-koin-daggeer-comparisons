package com.sample.hilt_koin_dagger_comparisons.di

import com.sample.feature_login.di.CurrentUserDaoModule
import com.sample.feature_login.di.LoginDependencies
import com.sample.feature_login.di.LoginModule
import com.sample.feature_login.ui.LoginNavigator
import com.sample.feature_todo.di.TodoDependencies
import com.sample.feature_todo.ui.ToDoListNavigator
import com.sample.hilt_koin_dagger_comparisons.Navigator
import dagger.Binds
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Module
interface NavigatorModule {

    @Binds
    fun bindLoginNavigator(impl: Navigator): LoginNavigator

    @Binds
    fun bindTodoNavigator(impl: Navigator): ToDoListNavigator
}

@Singleton
@Component(modules = [NavigatorModule::class, LoginModule::class, CurrentUserDaoModule::class])
interface MainComponent : LoginDependencies, TodoDependencies