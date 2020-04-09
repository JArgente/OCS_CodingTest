package com.olympic.channel.rover.application

import com.olympic.channel.rover.domain.Position
import com.olympic.channel.rover.domain.enums.Direction
import com.olympic.channel.rover.domain.enums.Terrain

data class Result(
        val path: List<Position>,
        val samples: List<Terrain>,
        val battery: Int,
        val position: Position,
        val facing: Direction
)