package com.olympic.channel.rover.domain.enums

enum class Direction {
    North,
    South,
    West,
    East;

    fun turnRight() : Direction =
            when (this) {
                North -> East
                East -> South
                South -> West
                West -> North
            }
    fun turnLeft() : Direction =
            when (this) {
                North -> West
                West -> South
                South -> East
                East -> North
            }
}