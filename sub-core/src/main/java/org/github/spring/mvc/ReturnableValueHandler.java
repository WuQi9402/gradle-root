package org.github.spring.mvc;

import java.io.IOException;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.github.spring.restful.Returnable;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Returnable返回类型解析器，Java版.
 *
 * @author JYD_XL
 * @see org.springframework.web.method.support.HandlerMethodReturnValueHandler
 */
@Slf4j
public class ReturnableValueHandler implements HandlerMethodReturnValueHandler {
  @Override
  public boolean supportsReturnType(@Nonnull MethodParameter returnType) {
    return Returnable.class.isAssignableFrom(returnType.getParameterType());
  }

  @Override
  public void handleReturnValue(Object returnValue, @Nonnull MethodParameter returnType, @Nonnull ModelAndViewContainer mavContainer, @Nonnull NativeWebRequest webRequest) throws IOException {
    val value = returnValue == null ? Returnable.nil() : ((Returnable) returnValue);
    val req   = Objects.requireNonNull(webRequest.getNativeRequest(HttpServletRequest.class));
    val resp  = Objects.requireNonNull(webRequest.getNativeResponse(HttpServletResponse.class));
    if(value.isTerminated()) {
      try {
        value.collect(req, resp);
      } catch(Exception e) {
        log.error(e.getMessage(), e);
        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      }
    } else {
      mavContainer.setViewName(value.get());
    }
    mavContainer.setRequestHandled(value.isTerminated());
    log.trace("Writing [{}] TO {}", value.getContentType(), value.get());
  }
}
