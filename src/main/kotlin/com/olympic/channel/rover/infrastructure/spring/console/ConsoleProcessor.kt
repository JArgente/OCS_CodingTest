package com.olympic.channel.rover.infrastructure.spring.console

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.olympic.channel.rover.application.BackOffService
import com.olympic.channel.rover.application.PathPlannerService
import com.olympic.channel.rover.application.Plan
import com.olympic.channel.rover.infrastructure.spring.console.models.PropertiesFile
import com.olympic.channel.rover.infrastructure.spring.errors.BadFormatException
import com.olympic.channel.rover.infrastructure.spring.mappers.PlanMapper
import com.olympic.channel.rover.infrastructure.spring.mappers.ResultMapper
import com.olympic.channel.rover.infrastructure.spring.parsers.ActionParser
import com.olympic.channel.rover.infrastructure.spring.parsers.BackoffStrategiesParser
import com.olympic.channel.rover.infrastructure.spring.validators.PlanDtoValidator
import com.olympic.channel.rover.infrastructure.spring.web.model.PlanDto
import org.slf4j.LoggerFactory
import java.io.File

class ConsoleProcessor() {

    private val logger = LoggerFactory.getLogger(this.javaClass.name)
    val om = ObjectMapper().registerModule(KotlinModule())
    fun processFiles(inputFile: File, outputFile: File) : Int{
        val validator = PlanDtoValidator();
        val backOffService = BackOffService(BackoffStrategiesParser.parseBackoffStrategies())
        val pathPlannerService = PathPlannerService(backOffService)
        try {
            val planDto: PlanDto = om.readValue(inputFile, PlanDto::class.java)
            validator.validateInput(planDto)
            val plan = PlanMapper.toPlan(planDto)
            val resultDto = ResultMapper.toResultDto(
                    pathPlannerService.executePlan(plan))
            om.writeValue(outputFile, resultDto)
            return 0
        }catch (e: BadFormatException){
            logger.error(e.reason)
            return -1
        }catch (e: MissingKotlinParameterException){
            logger.error(e.parameter.name +" is null")
            return -2
        }


    }
}