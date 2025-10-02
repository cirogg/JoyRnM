package com.cirogg.joyrnm.data.local

import com.cirogg.joyrnm.data.remote.dto.CharacterDto
import com.cirogg.joyrnm.data.remote.dto.EpisodeDto
import com.cirogg.joyrnm.data.remote.dto.LocationDto
import com.cirogg.joyrnm.domain.model.Character
import com.cirogg.joyrnm.domain.model.Episode
import com.cirogg.joyrnm.domain.model.Location
import com.cirogg.rnm.db.CharacterEntity

fun CharacterDto.toModel() = Character(
    id = id.toLong(),
    name = name,
    species = species,
    status = status,
    imageUrl = image,
    originName = origin.name,
    originUrl = origin.url,
    locationName = location.name,
    locationUrl = location.url,
    episodeUrls = episode
)

fun Character.toEntity() = CharacterEntity(
    id = id,
    name = name,
    species = species,
    status = status,
    imageUrl = imageUrl,
    originName = originName,
    originUrl = originUrl ?: "",
    locationName = locationName,
    locationUrl = locationUrl ?: "",
    episodeUrls = episodeUrls.joinToString(",")
)

fun CharacterEntity.toModel() = Character(
    id = id,
    name = name,
    species = species,
    status = status,
    imageUrl = imageUrl,
    originName = originName,
    originUrl = originUrl,
    locationName = locationName,
    locationUrl = locationUrl,
    episodeUrls = episodeUrls.split(",")?.filter { it.isNotBlank() } ?: emptyList()
)

fun LocationDto.toDomain() = Location(
    name = name,
    type = type,
    dimension = dimension
)

fun EpisodeDto.toDomain() = Episode(
    id = id.toLong(),
    name = name,
    episode = episode,
    airDate = air_date
)