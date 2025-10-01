package com.cirogg.joyrnm.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cirogg.joyrnm.core.util.Result
import com.cirogg.joyrnm.domain.usecase.GetCharactersPageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CharacterListViewModel(
    private val getCharactersPage: GetCharactersPageUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterListState())
    val state: StateFlow<CharacterListState> = _state.asStateFlow()

    fun loadNextPage() {
        val currentState = _state.value

        if (currentState.isLoading || currentState.endReached) return

        val nextPage = currentState.currentPage + 1
        _state.value = currentState.copy(isLoading = true)

        viewModelScope.launch {
            when (val result = getCharactersPage(nextPage)) {
                is Result.Success -> {
                    val newList = currentState.characters + result.data
                    _state.value = currentState.copy(
                        isLoading = false,
                        characters = newList,
                        currentPage = nextPage,
                        endReached = result.data.isEmpty()
                    )
                }
                is Result.Error -> {
                    _state.value = currentState.copy(
                        isLoading = false,
                        error = result.throwable.message
                    )
                }
                Result.Loading -> {
                    _state.value = currentState.copy(isLoading = true)
                }
            }
        }
    }
}