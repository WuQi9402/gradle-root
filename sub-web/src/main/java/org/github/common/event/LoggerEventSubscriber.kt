package org.github.common.event

import com.google.common.eventbus.AllowConcurrentEvents
import com.google.common.eventbus.Subscribe
import org.github.event.EventSubscriber
import org.github.ops.log
import org.github.ops.spring.json
import org.github.ops.trace
import org.springframework.stereotype.Component

@Component
class LoggerEventSubscriber: EventSubscriber {
  /** log. */
  private val log = LoggerEventSubscriber::class.log

  @AllowConcurrentEvents
  @Subscribe
  fun log(event: Any) {
    log.trace { event.json() }
  }
}
