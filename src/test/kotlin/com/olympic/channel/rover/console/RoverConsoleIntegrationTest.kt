package com.olympic.channel.rover.console

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.olympic.channel.rover.infrastructure.spring.console.ConsoleProcessor
import com.olympic.channel.rover.infrastructure.spring.web.model.ResultDto
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.core.io.ClassPathResource
import java.io.File
import java.io.FileInputStream
import java.io.FileReader

class RoverConsoleIntegrationTest {
    val INPUT_CORRECT_1 = "tests/test_run_1.json"
    val INPUT_CORRECT_2 = "tests/test_run_2.json"

    val INPUT_INCORRECT_BAD_TERRAIN_FORMAT = "tests/test_run_bad_terrain_format.json"
    val INPUT_INCORRECT_NULL_TERRAIN = "tests/test_run_null_terrain.json"
    val INPUT_INCORRECT_INVALID_POSITION = "tests/test_run_invalid_position.json"

    val OUTPUT_FILE_1 = "test_out_1.json"
    val OUTPUT_FILE_2 = "test_out_1.json"

    val OUTPUT_FILE_BAD_TERRAIN = "test_out_bad_terrain.json"
    val OUTPUT_FILE_NULL_TERRAIN = "test_out_null_terrain.json"
    val OUTPUT_FILE_INVALID_POSITION = "test_out_invalid_position.json"

    val OUTPUT_CORRECT_1 = "tests/test_sol_1.json"
    val OUTPUT_CORRECT_2 = "tests/test_sol_2.json"
    val om = ObjectMapper().registerModule(KotlinModule())

    @Test
    fun `When passing a correct input file it writes the ouput in a file and returns 0`(){
        val iFile = ClassPathResource(INPUT_CORRECT_1).file
        val oFile = File(OUTPUT_FILE_1)
        val result = ConsoleProcessor().processFiles(iFile, oFile)

        val expectedSol = ClassPathResource(OUTPUT_CORRECT_1).file
        val outputJson = om.readValue(oFile, ResultDto::class.java)
        val expectedJson = om.readValue(expectedSol, ResultDto::class.java)
        Assertions.assertThat(expectedJson).isEqualTo(outputJson)
        Assertions.assertThat(result).isEqualTo(0)
    }

    @Test
    fun `When passing another correct input file it writes the ouput in a file and returns 0`(){
        val iFile = ClassPathResource(INPUT_CORRECT_2).file
        val oFile = File(OUTPUT_FILE_2)
        val result = ConsoleProcessor().processFiles(iFile, oFile)

        val expectedSol = ClassPathResource(OUTPUT_CORRECT_2).file
        val outputJson = om.readValue(oFile, ResultDto::class.java)
        val expectedJson = om.readValue(expectedSol, ResultDto::class.java)
        Assertions.assertThat(expectedJson).isEqualTo(outputJson)
        Assertions.assertThat(result).isEqualTo(0)
    }

    @Test
    fun `When passing another incorrect input file with an incorrect terrain format it doesn't throw an exception and doesn't write the output file and returns -1`(){
        val iFile = ClassPathResource(INPUT_INCORRECT_BAD_TERRAIN_FORMAT).file
        val oFile = File(OUTPUT_FILE_BAD_TERRAIN)
        val result = ConsoleProcessor().processFiles(iFile, oFile)

        Assertions.assertThat(!oFile.exists())
        Assertions.assertThat(result).isEqualTo(-1)
    }

    @Test
    fun `When passing another incorrect input file with a null terrain it doesn't throw an exception and doesn't write the output file  and returns -2`(){
        val iFile = ClassPathResource(INPUT_INCORRECT_NULL_TERRAIN).file
        val oFile = File(OUTPUT_FILE_NULL_TERRAIN)
        val result = ConsoleProcessor().processFiles(iFile, oFile)

        Assertions.assertThat(!oFile.exists())
        Assertions.assertThat(result).isEqualTo(-2)
    }

    @Test
    fun `When passing another incorrect input file with an invalid initial position it doesn't throw an exception and doesn't write the output file and returns -1`(){
        val iFile = ClassPathResource(INPUT_INCORRECT_INVALID_POSITION).file
        val oFile = File(OUTPUT_FILE_INVALID_POSITION)
        val result = ConsoleProcessor().processFiles(iFile, oFile)

        Assertions.assertThat(!oFile.exists())
        Assertions.assertThat(result).isEqualTo(-1)
    }
}