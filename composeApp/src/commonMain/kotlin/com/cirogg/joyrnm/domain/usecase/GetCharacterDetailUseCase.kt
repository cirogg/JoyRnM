package com.cirogg.joyrnm.domain.usecase

import com.cirogg.joyrnm.domain.repository.CharacterRepository
import com.cirogg.joyrnm.core.util.Result
import com.cirogg.joyrnm.data.local.toDomain
import com.cirogg.joyrnm.domain.model.CharacterDetail


class GetCharacterDetailUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(id: Long): Result<CharacterDetail> {
        val character = repository.getCharacterById(id)
            ?: return Result.Error(IllegalStateException("Character not found"))

        val location = character.locationUrl
            ?.takeIf { it.isNotBlank() }
            ?.let { url ->
                when (val res = repository.getLocation(url)) {
                    is Result.Success -> res.data.toDomain()
                    is Result.Error -> null
                    Result.Loading -> null
                }
            }

        val episodes = character.episodeUrls.mapNotNull { url ->
            when (val res = repository.getEpisode(url)) {
                is Result.Success -> res.data.toDomain()
                else -> null
            }
        }

        return Result.Success(CharacterDetail(character, location, episodes))
    }
}