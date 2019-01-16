package org.github.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@EnableWebSocketMessageBroker
@Configuration
class WebSocketMessageBrokerConfig : WebSocketMessageBrokerConfigurer {
  override fun registerStompEndpoints(registry: StompEndpointRegistry) {
    registry.addEndpoint("/endpoint").withSockJS()
  }

  override fun configureMessageBroker(registry: MessageBrokerRegistry) {
    registry.enableSimpleBroker("/topic")
  }
}