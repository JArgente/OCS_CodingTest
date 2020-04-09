package com.olympic.channel.rover.infrastructure.spring.web.model

data class PositionDto(
        val location: LocationDto,
        val facing: String
)