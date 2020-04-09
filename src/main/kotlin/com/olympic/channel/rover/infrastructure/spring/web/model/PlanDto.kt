package com.olympic.channel.rover.infrastructure.spring.web.model

class PlanDto (
        val terrain: List<List<String>>,
        val battery: Int,
        val commands: List<String>,
        val initialPosition: PositionDto
)