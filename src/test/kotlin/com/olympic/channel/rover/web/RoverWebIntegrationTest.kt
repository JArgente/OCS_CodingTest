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

    val OUTPUT_CORRECT_1 = "tests/test_sol_1.json"
    val OUTPUT_CORRECT_2 = "tests/test_sol_2.json"
    val om = ObjectMapper().registerModule(KotlinModule())

    @Test
    fun `When a correct file is passed it returns a correct output`() {
        val iFile = ClassPathResource(INPUT_CORRECT_1).file
        val oFile = ClassPathResource(OUTPUT_CORRECT_1).file
        val planDto: PlanDto = om.readValue(iFile, PlanDto::class.java)
        val response = restTemplate.postForEntity("/plans", planDto, String::class.java)

        val entity = om.readValue(response.body, ResultDto::class.java)
        val responseTest = om.readValue(oFile, ResultDto::class.java)

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(entity).isEqualTo(responseTest)
    }

    @Test
    fun `When another file is passed it returns a correct output`() {
        val iFile = ClassPathResource(INPUT_CORRECT_2).file
        val oFile = ClassPathResource(OUTPUT_CORRECT_2).file
        val planDto: PlanDto = om.readValue(iFile, PlanDto::class.java)
        val response = restTemplate.postForEntity("/plans", planDto, String::class.java)

        val entity = om.readValue(response.body, ResultDto::class.java)
        val responseTest = om.readValue(oFile, ResultDto::class.java)

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        Assertions.assertThat(entity).isEqualTo(responseTest)
    }


}