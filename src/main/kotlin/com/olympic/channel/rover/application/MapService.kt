package com.olympic.channel.rover.application

import com.olympic.channel.rover.domain.Map
import com.olympic.channel.rover.domain.Position
import com.olympic.channel.rover.domain.enums.Terrain

data class MapService(val terrain: List<List<Terrain>>): Map {

    override fun getPosition(position: Position): Terrain =
            terrain[position.y][position.x]

    override fun isObstacle(position: Position): Boolean =
            terrain[position.y][position.x] == Terrain.Obstacle

}