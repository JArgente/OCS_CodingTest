package com.olympic.channel.rover.infrastructure.spring.web.model

data class PlanDto (
        val terrain: List<List<String>>,
        val battery: Int,
        val commands: List<String>,
        val initialPosition: PositionDto
)

data class PositionDto(
        val location: LocationDto,
        val facing: String
)

data class LocationDto (
        val x: Int,

        val y: Int
)