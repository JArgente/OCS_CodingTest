package com.olympic.channel.rover.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.olympic.channel.rover.infrastructure.spring.RoverApplication
import com.olympic.channel.rover.infrastructure.spring.web.model.PlanDto
import com.olympic.channel.rover.infrastructure.spring.web.model.ResultDto
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpStatus
import java.io.File

@SpringBootTest(classes = [RoverApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RoverWebIntegrationTest(@Autowired val restTemplate: TestRestTemplate) {

    val INPUT_CORRECT_1 = "tests/test_run_1.json"
    val INPUT_CORRECT_2 = "tests/test_run_2.json"

    val INPUT_INCORRECT_BAD_TERRAIN_FORMAT = "tests/test_run_bad_terrain_format.json"
    val INPUT_INCORRECT_INVALID_POSITION = "tests/test_run_invalid_position.json"

    val OUTPUT_CORRECT_1 = "tests/test_sol_1.json"
    val OUTPUT_CORRECT_2 = "tests/test_sol_2.json"
    val om = ObjectMapper().registerModule(KotlinModule())

    @Test
    fun `Whe correct plan is passed it returns 200 and a correct result`() {
        val iFile = ClassPathResource(INPUT_CORRECT_1).file
        val oFile = ClassPathResource(OUTPUT_CORRECT_1).file
        val planDto: PlanDto = om.readValue(iFile, PlanDto::class.java)
        val response = restTemplate.postForEntity("/rover/v1/plans", planDto, String::class.java)

        val entity = om.readValue(response.body, ResultDto::class.java)
        val responseTest = om.readValue(oFile, ResultDto::class.java)

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(entity).isEqualTo(responseTest)
    }

    @Test
    fun `When another correct plan is passed it returns 200 and a correct result`() {
        val iFile = ClassPathResource(INPUT_CORRECT_2).file
        val oFile = ClassPathResource(OUTPUT_CORRECT_2).file
        val planDto: PlanDto = om.readValue(iFile, PlanDto::class.java)
        val response = restTemplate.postForEntity("/rover/v1/plans", planDto, String::class.java)

        val entity = om.readValue(response.body, ResultDto::class.java)
        val responseTest = om.readValue(oFile, ResultDto::class.java)

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(entity).isEqualTo(responseTest)
    }

    @Test
    fun `When an input with a incorrect format is sent it returns 400 Bad request`() {
        val iFile = ClassPathResource(INPUT_INCORRECT_BAD_TERRAIN_FORMAT).file
        val planDto: PlanDto = om.readValue(iFile, PlanDto::class.java)
        val response = restTemplate.postForEntity("/rover/v1/plans", planDto, String::class.java)

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `When an input with a invalid initial position is sent it returns 400 Bad request`() {
        val iFile = ClassPathResource(INPUT_INCORRECT_INVALID_POSITION).file
        val planDto: PlanDto = om.readValue(iFile, PlanDto::class.java)
        val response = restTemplate.postForEntity("/rover/v1/plans", planDto, String::class.java)

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }


}