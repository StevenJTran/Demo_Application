package com.example.demoapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userManager: UserManager
) : ViewModel() {

    private val _userInputUpdated = MutableLiveData<Boolean>().apply { value = false }
    val userInputUpdated: LiveData<Boolean> get() = _userInputUpdated

    fun onAction(action: Action) {
        when (action) {
            is Action.SaveUserInput -> {
                saveUserInputToSharedPrefs(action.userInput)
            }
        }
    }

    fun getSavedUserInput(): String {
        return userManager.userInput
    }

    private fun saveUserInputToSharedPrefs(userInput: String) {
        userManager.userInput = userInput
        _userInputUpdated.postValue(true)
    }

    sealed class Action {
        data class SaveUserInput(val userInput: String) : Action()
    }

}