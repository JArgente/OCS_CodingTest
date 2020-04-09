package com.olympic.channel.rover.infrastructure.spring.web

import com.olympic.channel.rover.application.PathPlannerService
import com.olympic.channel.rover.infrastructure.spring.mappers.PlanMapper
import com.olympic.channel.rover.infrastructure.spring.mappers.ResultMapper
import com.olympic.channel.rover.infrastructure.spring.web.model.PlanDto
import com.olympic.channel.rover.infrastructure.spring.web.model.ResultDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/rover/v1")
class RoverController(
        val pathPlannerService: PathPlannerService,
        val planMapper: PlanMapper,
        val resultMapper: ResultMapper) {

    @PostMapping("/plans")
    fun executeMovementPlan(@RequestBody request: PlanDto): ResponseEntity<ResultDto> {
        val plan = planMapper.toPlan(request)
        val result = pathPlannerService.executePlan(plan)
        val resultDto = resultMapper.toResultDto(result)
        return ResponseEntity.ok(resultDto)
    }
}