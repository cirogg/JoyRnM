package com.cirogg.joyrnm.presentation.list

import com.cirogg.joyrnm.domain.model.Character

data class CharacterListState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val currentPage: Int = 0
)