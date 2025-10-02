package com.cirogg.joyrnm.domain.model

data class Character(
    val id: Long,
    val name: String,
    val species: String,
    val status: String,
    val imageUrl: String,
    val originName: String,
    val originUrl: String?,
    val locationName: String,
    val locationUrl: String?,
    val episodeUrls: List<String>
)