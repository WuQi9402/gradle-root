package org.github.logger;

import java.util.List;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import static ch.qos.logback.core.spi.FilterReply.*;
import static java.util.List.*;

public class NameFilter extends Filter<ILoggingEvent> {
  private List<Class<?>> classes = of();

  @Override
  public void start() {
    if(classes != null && !classes.isEmpty()) super.start();
  }

  @Override
  public FilterReply decide(ILoggingEvent event) {
    if(!isStarted()) return NEUTRAL;
    for(Class<?> name : classes) {
      if(event.getLoggerName().equals(name.getName())) return NEUTRAL;
    }
    return DENY;
  }

  public void setClasses(List<Class<?>> classes) {
    this.classes = classes;
  }
}
