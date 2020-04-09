package com.olympic.channel.rover.infrastructure.spring.errors

import java.lang.RuntimeException

data class BadFormatException(val reason: String) : RuntimeException(reason)