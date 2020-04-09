package com.olympic.channel.rover.infrastructure.spring.config

import com.olympic.channel.rover.application.BackOffService
import com.olympic.channel.rover.application.PathPlannerService
import com.olympic.channel.rover.infrastructure.spring.parsers.BackoffStrategiesParser
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@RequiredArgsConstructor
class SpringBootServiceConfig {

  @Bean
  fun backoffService(): BackOffService {
    val backOffStrategyActions = BackoffStrategiesParser.parseBackoffStrategies()
    return BackOffService(backOffStrategyActions);
  }

  @Bean
  fun pathPlannerService(): PathPlannerService =
          PathPlannerService(backoffService());

}
