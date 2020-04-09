package com.olympic.channel.rover.application

import com.olympic.channel.rover.domain.Action
import com.olympic.channel.rover.domain.Position
import com.olympic.channel.rover.domain.enums.Direction
import com.olympic.channel.rover.domain.enums.Terrain

data class Plan (
        val terrain: List<List<Terrain>>,
        val battery: Int,
        val commands: List<Action>,
        val initialPosition: Position,
        val facing: Direction
)