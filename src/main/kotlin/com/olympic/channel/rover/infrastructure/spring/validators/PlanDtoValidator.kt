package com.olympic.channel.rover.infrastructure.spring.validators

import com.olympic.channel.rover.infrastructure.spring.errors.BadFormatException
import com.olympic.channel.rover.infrastructure.spring.web.model.PlanDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.Errors
import org.springframework.validation.FieldError
import org.springframework.validation.Validator
import java.util.*

@Component
class PlanDtoValidator {

    fun validateInput(planDto: PlanDto) {
        var message = mutableListOf<String>()
        if(planDto.terrain.isEmpty() || planDto.terrain.any { it.isEmpty() })
            message.add("Some terrain rows are empty")
        val initialX = planDto.initialPosition.location.x
        val initialY = planDto.initialPosition.location.y
        if(initialY >= planDto.terrain.size || initialY < 0
                || initialX >= planDto.terrain[initialY].size || initialX < 0)
            message.add("Invalid initial position")
        if(planDto.battery < 0)
            message.add("Battery must be non negative number")

        if(message.isNotEmpty())
            onValidationErrors(message)
    }

    private fun onValidationErrors(messages: List<String>){
        val errors = messages.joinToString(separator = ", ")
        throw BadFormatException(
                reason = errors )
    }

}