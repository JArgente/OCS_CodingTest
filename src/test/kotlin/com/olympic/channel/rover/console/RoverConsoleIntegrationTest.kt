package com.olympic.channel.rover.console

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.olympic.channel.rover.infrastructure.spring.console.ConsoleProcessor
import com.olympic.channel.rover.infrastructure.spring.web.model.ResultDto
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.core.io.ClassPathResource

class RoverConsoleIntegrationTest {
    val INPUT_CORRECT_1 = "tests/test_run_1.json"
    val INPUT_CORRECT_2 = "tests/test_run_2.json"

    val OUTPUT_FILE = "tests/test_out_1.json"

    val OUTPUT_CORRECT_1 = "tests/test_sol_1.json"
    val OUTPUT_CORRECT_2 = "tests/test_sol_2.json"
    val om = ObjectMapper().registerModule(KotlinModule())

    @Test
    fun `When passing a correct input file it writes the ouput in a file`(){
        val iFile = ClassPathResource(INPUT_CORRECT_1).file
        val oFile = ClassPathResource(OUTPUT_FILE).file
        ConsoleProcessor().processFiles(iFile, oFile)

        val expectedSol = ClassPathResource(OUTPUT_CORRECT_1).file
        val outputJson = om.readValue(oFile, ResultDto::class.java)
        val expectedJson = om.readValue(expectedSol, ResultDto::class.java)
        Assertions.assertThat(expectedJson).isEqualTo(outputJson)
    }

    @Test
    fun `When passing another correct input file it writes the ouput in a file`(){
        val iFile = ClassPathResource(INPUT_CORRECT_2).file
        val oFile = ClassPathResource(OUTPUT_FILE).file
        ConsoleProcessor().processFiles(iFile, oFile)

        val expectedSol = ClassPathResource(OUTPUT_CORRECT_2).file
        val outputJson = om.readValue(oFile, ResultDto::class.java)
        val expectedJson = om.readValue(expectedSol, ResultDto::class.java)
        Assertions.assertThat(expectedJson).isEqualTo(outputJson)
    }
}