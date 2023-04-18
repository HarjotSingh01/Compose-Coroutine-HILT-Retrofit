package com.example.coroutinesretrofit.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinesretrofit.domain.repo.UserNameRepo
import com.example.coroutinesretrofit.utils.BaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Harjot Singh on 18/04/23
 */
@HiltViewModel
internal class UserNameViewModel @Inject constructor(private val repo: UserNameRepo) : ViewModel() {
    private val _uiState = mutableStateOf<BaseResult<List<String>>>(BaseResult.Loading)

    val uiState: State<BaseResult<List<String>>> = _uiState

    fun fetchUserNames() {
        viewModelScope.launch {
            _uiState.value = BaseResult.Loading
            try {
                delay(3000)
                val response = repo.fetchUserNames()
                val data = response.body()
                if (response.isSuccessful && !data.isNullOrEmpty()) {
                    _uiState.value = BaseResult.Success(data)
                } else {
                    _uiState.value =
                        BaseResult.Error(exception = java.lang.Exception("Response body is empty"))
                }
            } catch (ex: Exception) {
                _uiState.value = BaseResult.Error(exception = ex)
            }
        }
    }
}