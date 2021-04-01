package com.joesamyn.envelope.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joesamyn.envelope.models.AuthResp
import com.joesamyn.envelope.models.UserLogin
import com.joesamyn.envelope.repositories.interfaces.AuthenticationRepository
import com.joesamyn.envelope.ui.fragment.Home
import com.joesamyn.envelope.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthenticationRepository) : ViewModel() {

    private val LOG = LoginViewModel::class.java.simpleName

    var username: String = ""
    var password: String = ""

    private var _dataState: MutableLiveData<DataState<AuthResp>> = MutableLiveData()
    val dataState: LiveData<DataState<AuthResp>>
        get() = _dataState

    fun setStateEvent(stateEvent: LoginStateEvent){
        viewModelScope.launch {
            when(stateEvent) {
                is LoginStateEvent.LoginEvent -> {
                    login()
                }
            }
        }
    }


    /**
     * Attempts to authenticate user with Username and Password provided
     */
    fun login() {
        viewModelScope.launch {
            val user = UserLogin(username, password)
            authRepository.authenticate(user)
                .onEach { dataState ->
                    _dataState.value = dataState
                    Log.i("Login Repo","State updated")
                }.launchIn(viewModelScope)
        }
    }
}

sealed class LoginStateEvent {

    /**
     * Signifies a login attempt is in progress
     */
    object LoginEvent: LoginStateEvent()

    /**
     * Stable state, nothing happening and waiting for event from user
     */
    object None: LoginStateEvent()
}