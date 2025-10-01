package com.cirogg.joyrnm.domain.model

data class Character(
    val id: Long,
    val name: String,
    val species: String,
    val status: String,
    val imageUrl: String,
    val originName: String,
    val locationName: String
)