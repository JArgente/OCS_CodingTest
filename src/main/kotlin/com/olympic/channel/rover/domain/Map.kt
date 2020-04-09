package com.olympic.channel.rover.domain

import com.olympic.channel.rover.domain.enums.Terrain

interface Map {

    fun getPosition(position: Position): Terrain

    fun isObstacle(position: Position): Boolean

}