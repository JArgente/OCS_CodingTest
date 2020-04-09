package com.olympic.channel.rover.infrastructure.spring.parsers

import com.olympic.channel.rover.domain.enums.Direction
import com.olympic.channel.rover.domain.enums.Terrain
import com.olympic.channel.rover.infrastructure.spring.errors.BadFormatException

object TerrainParser {

    fun toTerrain(terrainString: String): Terrain =
            when(terrainString.trim()){
                "Fe" -> Terrain.Ferrum
                "Se" -> Terrain.Selenium
                "W" -> Terrain.Water
                "Si" -> Terrain.Silicon
                "Zn" -> Terrain.Zinc
                "Obs" -> Terrain.Obstacle
                else -> throw BadFormatException("Invalid terrain format")
            }

    fun toDto(terrain: Terrain): String =
            when(terrain){
                 Terrain.Ferrum -> "Fe"
                 Terrain.Selenium -> "Se"
                 Terrain.Water -> "W"
                 Terrain.Silicon -> "Si"
                 Terrain.Zinc -> "Zn"
                 Terrain.Obstacle -> "Obs"
            }
}