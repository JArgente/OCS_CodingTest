package com.olympic.channel.rover.infrastructure.spring.parsers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.olympic.channel.rover.domain.Action

object BackoffStrategiesParser {
    val backOffStrategy = """
        [
            ["E","R","F"],
            ["E","L","F"],
            ["E","L","L","F"],
            ["E","B","R","F"],
            ["E","B","B","L","F"],
            ["E","F","F"],
            ["E","F","L","F","L","F"]
        ]
    """.trimIndent()

    val om = ObjectMapper().registerModule(KotlinModule())

    fun parseBackoffStrategies():List<List<Action>> {
        val listaBackoff: List<List<String>> = om.readValue(backOffStrategy, List::class.java) as List<List<String>>
        return listaBackoff.map { lista -> lista.map { ActionParser.toAction(it) } }
    }

}