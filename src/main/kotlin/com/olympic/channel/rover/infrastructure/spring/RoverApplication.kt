package com.olympic.channel.rover.infrastructure.spring

import com.olympic.channel.rover.infrastructure.spring.console.ConsoleProcessor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import java.io.File

@SpringBootApplication(scanBasePackages = ["com.olympic.channel.rover.infrastructure"])
class RoverApplication

fun main(args: Array<String>) {

	val consoleProcessor = ConsoleProcessor()
	if(args.size == 2){
		val input = File(args[0])
		val output = File(args[1])
		consoleProcessor.processFiles(input, output)
	}else
		runApplication<RoverApplication>(*args)
}
