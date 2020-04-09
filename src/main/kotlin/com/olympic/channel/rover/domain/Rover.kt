package com.olympic.channel.rover.domain

import com.olympic.channel.rover.domain.enums.ActionResult
import com.olympic.channel.rover.domain.enums.Direction
import com.olympic.channel.rover.domain.enums.Terrain
import lombok.extern.log4j.Log4j2
import org.slf4j.LoggerFactory

@Log4j2
data class Rover(var position: Position, var battery: Int, var facing: Direction, val map: Map){

    val path: MutableList<Position> = mutableListOf(position)
    val samples: MutableList<Terrain> = mutableListOf()
    private val logger = LoggerFactory.getLogger(this.javaClass.name)

    fun doAction(action: Action): ActionResult {
        return when (action) {
            Forward -> processAction(action, this::checkMove, this::moveForward)
            Backwards -> processAction(action, this::checkMove, this::moveBackwards)
            TurnLeft -> processAction(action, this::checkBattery, this::turnLeft)
            TurnRight -> processAction(action, this::checkBattery, this::turnRight)
            SolarPanels -> processAction(action, this::checkBattery, this::extendSolarPanels)
            TakeSample -> processAction(action, this::checkBattery, this::takeSample)
        }
    }

    private fun moveForward() {
        logger.info("Moving from: " + position +" to "+getNextPosition(true))
        position = getNextPosition(true)
        path.add(position)
    }

    private fun moveBackwards() {
        logger.info("Moving from: " + position +" to "+getNextPosition(false))
        position = getNextPosition(false)
        path.add(position)
    }

    private fun turnRight() {
        logger.info("Turning Right from: " + facing +" to "+facing.turnRight())
        facing = facing.turnRight()
    }

    private fun turnLeft() {
        logger.info("Turning left from: " + facing +" to "+facing.turnLeft())

        facing = facing.turnLeft()
    }

    private fun extendSolarPanels() {
        logger.info("Recharging battery from: " + battery +" to  "+(battery+10))
        battery += 10
    }

    private fun takeSample() {
        logger.info("Taken sample: " + map.getPosition(position) +" from position: "+position)
        samples.add(map.getPosition(position))
    }

    private fun processAction(action: Action, isItPossible: (Action) -> ActionResult, actionFunction: () -> Unit): ActionResult {
        val result = isItPossible(action)
        if(result == ActionResult.Ok) {
            actionFunction()
            battery -= action.battery
        }
        return result
    }

    private fun checkBattery(action: Action): ActionResult =
            if(battery >= action.battery)
                ActionResult.Ok
            else
                ActionResult.OffBatery

    private fun checkMove(action: Action): ActionResult =
            if(map.isObstacle(getNextPosition(action is Forward)))
                ActionResult.Obstacle
            else
                checkBattery(action)


    private fun getNextPosition(forward: Boolean) : Position {
        val step = if (forward) 1 else -1
        return when (facing) {
            Direction.North -> Position(position.x, position.y - step)
            Direction.South -> Position(position.x, position.y + step)
            Direction.East -> Position(position.x + step, position.y)
            Direction.West -> Position(position.x - step, position.y)
        }
    }

}