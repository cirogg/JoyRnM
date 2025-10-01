package com.cirogg.joyrnm.data.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class PagedDto<T>(
    val info: PageInfoDto,
    val results: List<T>
)

@Serializable
data class PageInfoDto(
    val count: Int,
    val pages: Int,
    val next: String? = null,
    val prev: String? = null
)