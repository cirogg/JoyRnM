package com.cirogg.joyrnm.presentation.detail

import com.cirogg.joyrnm.domain.model.Character
import com.cirogg.joyrnm.domain.model.Episode
import com.cirogg.joyrnm.domain.model.Location

data class CharacterDetailState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val character: Character? = null,
    val location: Location? = null,
    val episodes: List<Episode> = emptyList()
)