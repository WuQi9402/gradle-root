package org.github.config

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.boot.autoconfigure.jackson.JacksonProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ofPattern

@Configuration
class JacksonConfig {
  @Bean
  fun jackson2ObjectMapperBuilderCustomizer(serializer: LocalDateTimeSerializer) = Jackson2ObjectMapperBuilderCustomizer { it.serializerByType(LocalDateTime::class.java, serializer) }

  @Bean
  fun localDateTimeSerializer(props: JacksonProperties) = LocalDateTimeSerializer(ofPattern(props.dateFormat))
}
