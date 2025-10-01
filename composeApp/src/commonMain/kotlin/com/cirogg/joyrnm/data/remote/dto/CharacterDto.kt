package com.cirogg.joyrnm.data.remote.dto


import kotlinx.serialization.Serializable

@Serializable
data class ApiLocationDto(
    val name: String,
    val url: String
)

@Serializable
data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String = "",
    val gender: String,
    val origin: ApiLocationDto,
    val location: ApiLocationDto,
    val image: String,
    val episode: List<String> = emptyList(),
    val url: String,
    val created: String
)