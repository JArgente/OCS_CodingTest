package com.olympic.channel.rover.infrastructure.spring.mappers

import com.olympic.channel.rover.application.Result
import com.olympic.channel.rover.infrastructure.spring.parsers.DirectionParser
import com.olympic.channel.rover.infrastructure.spring.parsers.TerrainParser
import com.olympic.channel.rover.infrastructure.spring.web.model.LocationDto
import com.olympic.channel.rover.infrastructure.spring.web.model.PositionDto
import com.olympic.channel.rover.infrastructure.spring.web.model.ResultDto
import org.springframework.stereotype.Component

@Component
object ResultMapper {
    fun toResultDto(result: Result): ResultDto{
        val visitedCells = result.path.map { LocationDto(it.x, it.y) }
        val samplesCollected = result.samples.map { TerrainParser.toDto(it) }
        val finalPosition = PositionDto(LocationDto(result.position.x, result.position.y), DirectionParser.toDto(result.facing))

        return ResultDto(visitedCells, samplesCollected, result.battery, finalPosition)
    }
}