package com.cirogg.joyrnm.domain.model

data class CharacterDetail(
    val character: Character,
    val location: Location?,
    val episodes: List<Episode>
)