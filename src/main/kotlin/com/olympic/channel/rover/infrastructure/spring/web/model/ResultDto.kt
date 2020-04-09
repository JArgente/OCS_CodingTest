package com.olympic.channel.rover.infrastructure.spring.web.model

data class ResultDto (
        val visitedCells: List<LocationDto>,
        val samplesCollected: List<String>,
        val battery: Int,
        val finalPosition: PositionDto
)