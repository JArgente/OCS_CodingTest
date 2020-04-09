package com.olympic.channel.rover.infrastructure.spring.parsers

import com.olympic.channel.rover.domain.enums.Direction
import com.olympic.channel.rover.infrastructure.spring.errors.BadFormatException

object DirectionParser {

    fun toDirection(directionString: String): Direction=
            when(directionString.trim()){
                "North" -> Direction.North
                "East" -> Direction.East
                "South" -> Direction.South
                "West" -> Direction.West
                else -> throw BadFormatException("Invalid facing format")
            }

    fun toDto(direction: Direction): String =
            when(direction){
                 Direction.North -> "North"
                 Direction.East -> "East"
                 Direction.South -> "South"
                 Direction.West -> "West"
            }
}