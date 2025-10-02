package com.cirogg.joyrnm.domain.repository

import com.cirogg.joyrnm.core.util.Result
import com.cirogg.joyrnm.data.remote.dto.EpisodeDto
import com.cirogg.joyrnm.data.remote.dto.LocationDto
import com.cirogg.joyrnm.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacters(page: Int): Result<List<Character>>
    suspend fun getCharacterById(id: Long): Character?
    suspend fun getLocation(url: String): Result<LocationDto>
    suspend fun getEpisode(url: String): Result<EpisodeDto>
}