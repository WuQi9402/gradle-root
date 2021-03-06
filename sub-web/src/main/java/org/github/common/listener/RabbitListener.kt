package org.github.common.listener

import org.github.ops.info
import org.github.ops.log
import org.github.ops.spring.json
import org.github.spring.footstone.Entity
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class RabbitListener {
  /** log. */
  private val log = RabbitListener::class.log

  @RabbitListener(queues = ["app.queue"])
  fun listen(msg: Entity) {
    log.info { msg.json() }
  }
}
