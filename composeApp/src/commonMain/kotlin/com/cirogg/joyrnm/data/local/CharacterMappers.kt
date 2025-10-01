package com.cirogg.joyrnm.data.local

import com.cirogg.joyrnm.domain.model.Character
import com.cirogg.rnm.db.CharacterEntity

fun CharacterEntity.toModel(): Character = Character(
    id = this.id, // ya es Long
    name = this.name,
    species = this.species,
    status = this.status,
    imageUrl = this.imageUrl,
    originName = this.originName,
    locationName = this.locationName
)

fun Character.toEntity(): CharacterEntity = CharacterEntity(
    id = this.id,
    name = this.name,
    species = this.species,
    status = this.status,
    imageUrl = this.imageUrl,
    originName = this.originName,
    locationName = this.locationName
)