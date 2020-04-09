package com.olympic.channel.rover.application

import com.olympic.channel.rover.domain.*
import com.olympic.channel.rover.domain.enums.ActionResult
import com.olympic.channel.rover.domain.enums.Direction

class PathPlannerService(private val backOffService: BackOffService) {

    fun executePlan(plan: Plan):Result{
        val mapService = MapService(plan.terrain)
        val rover = Rover(plan.initialPosition, plan.battery, plan.facing, mapService)
        transmitActions(rover, plan.commands)
        return getResults(rover)
    }

    private fun transmitActions(rover:Rover, actions:List<Action>){
        actions.forEach {
            val result = rover.doAction(it)
            if(result == ActionResult.Obstacle)
                backOffService.applyBackOffStrategy(rover)
        }
    }

    private fun getResults(rover:Rover): Result =
            Result( path = rover.path,
                    samples = rover.samples,
                    battery = rover.battery,
                    position = rover.position,
                    facing = rover.facing)
}