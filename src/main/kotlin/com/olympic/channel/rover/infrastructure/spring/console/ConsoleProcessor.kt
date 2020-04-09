package com.olympic.channel.rover.infrastructure.spring.console

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.olympic.channel.rover.application.BackOffService
import com.olympic.channel.rover.application.PathPlannerService
import com.olympic.channel.rover.infrastructure.spring.console.models.PropertiesFile
import com.olympic.channel.rover.infrastructure.spring.mappers.PlanMapper
import com.olympic.channel.rover.infrastructure.spring.mappers.ResultMapper
import com.olympic.channel.rover.infrastructure.spring.parsers.ActionParser
import com.olympic.channel.rover.infrastructure.spring.parsers.BackoffStrategiesParser
import com.olympic.channel.rover.infrastructure.spring.web.model.PlanDto
import java.io.File

class ConsoleProcessor() {

    val om = ObjectMapper().registerModule(KotlinModule())

    fun processFiles(inputFile: File, outputFile: File){

        val backOffService = BackOffService(BackoffStrategiesParser.parseBackoffStrategies())
        val pathPlannerService = PathPlannerService(backOffService)

        val planDto: PlanDto = om.readValue(inputFile, PlanDto::class.java)
        val resultDto = ResultMapper.toResultDto(
                pathPlannerService.executePlan(
                        PlanMapper.toPlan(planDto)))
        om.writeValue(outputFile, resultDto)

    }
}