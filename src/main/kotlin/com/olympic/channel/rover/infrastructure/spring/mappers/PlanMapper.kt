package com.olympic.channel.rover.infrastructure.spring.mappers

import com.olympic.channel.rover.application.Plan
import com.olympic.channel.rover.domain.Position
import com.olympic.channel.rover.infrastructure.spring.errors.BadFormatException
import com.olympic.channel.rover.infrastructure.spring.parsers.ActionParser
import com.olympic.channel.rover.infrastructure.spring.parsers.DirectionParser
import com.olympic.channel.rover.infrastructure.spring.parsers.TerrainParser
import com.olympic.channel.rover.infrastructure.spring.web.model.PlanDto
import org.springframework.stereotype.Component

@Component
object PlanMapper {
    fun toPlan(planDto: PlanDto): Plan {
        val terrain = planDto.terrain
                .map { row -> row
                        .map { TerrainParser.toTerrain(it) } }
        val actions = planDto.commands.map { ActionParser.toAction(it) }
        val locationDto = planDto.initialPosition.location
        val facingDto = planDto.initialPosition.facing
        val direction = DirectionParser.toDirection(facingDto)
        val position = Position(locationDto.x, locationDto.y)

        return Plan(terrain, planDto.battery, actions, position, direction)
    }
}