package com.olympic.channel.rover.application

import com.olympic.channel.rover.domain.Action
import com.olympic.channel.rover.domain.Rover
import com.olympic.channel.rover.domain.enums.ActionResult

class BackOffService(val strategyList: List<List<Action>>) {

    fun applyBackOffStrategy(rover: Rover) {
        var count = 0
        var result: ActionResult = ActionResult.Obstacle
        while(count < strategyList.size && result != ActionResult.Ok){
            val strategy = strategyList[count]
            var countActions = 0
            result = ActionResult.Ok
            while(countActions < strategy.size && result == ActionResult.Ok){
                result = rover.doAction(strategy[countActions])
                countActions++
            }
            count++
        }
    }

}