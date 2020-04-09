package com.olympic.channel.rover.infrastructure.spring.parsers

import com.olympic.channel.rover.domain.*
import com.olympic.channel.rover.infrastructure.spring.errors.BadFormatException

object ActionParser {

    fun toAction(actionString: String): Action=
            when(actionString.trim()){
                "F" -> Forward
                "B" -> Backwards
                "L" -> TurnLeft
                "R" -> TurnRight
                "S" -> TakeSample
                "E" -> SolarPanels
                else -> throw BadFormatException("Invalid command format")
            }

    fun toDto(action: Action): String=
            when(action){
                 Forward -> "F"
                 Backwards -> "B"
                 TurnLeft -> "L"
                 TurnRight -> "R"
                 TakeSample -> "S"
                 SolarPanels -> "E"
            }
}