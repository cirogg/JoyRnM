package com.cirogg.joyrnm.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cirogg.joyrnm.domain.usecase.GetCharacterDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.cirogg.joyrnm.core.util.Result

class CharacterDetailViewModel(
    private val getCharacterDetail: GetCharacterDetailUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterDetailState())
    val state: StateFlow<CharacterDetailState> = _state

    fun loadCharacter(id: Long) {
        viewModelScope.launch {
            _state.value = CharacterDetailState(isLoading = true)
            when (val result = getCharacterDetail(id)) {
                is Result.Success -> {
                    _state.value = CharacterDetailState(
                        character = result.data.character,
                        location = result.data.location,
                        episodes = result.data.episodes,
                        isLoading = false
                    )
                }
                is Result.Error -> {
                    _state.value = CharacterDetailState(
                        error = result.throwable.message,
                        isLoading = false
                    )
                }
                Result.Loading -> _state.value = CharacterDetailState(isLoading = true)
            }
        }
    }
}