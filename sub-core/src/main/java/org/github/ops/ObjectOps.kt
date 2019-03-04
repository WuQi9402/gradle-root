package org.github.ops

import com.fasterxml.jackson.databind.ObjectMapper
import org.github.spring.bootstrap.AppCtxHolder
import org.springframework.cglib.beans.BeanMap
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.io.OutputStream

fun Any?.json() = objectMapper.writeValueAsString(this)!!

fun Any?.writeValue(output: OutputStream) = objectMapper.writeValue(output, this)

fun getReq() = (RequestContextHolder.getRequestAttributes() as? ServletRequestAttributes)?.request

fun getResp() = (RequestContextHolder.getRequestAttributes() as? ServletRequestAttributes)?.response

@Suppress("UNCHECKED_CAST")
fun Any.map() = BeanMap.create(this).toMutableMap() as MutableMap<String, Any?>

fun <T: Any> Map<String, Any?>.bean(clazz: Class<T>) = clazz.getDeclaredConstructor().newInstance()!!.also { BeanMap.create(it).putAll(this) }

fun <T: Any> T.proxy() = appCtx.getBean(this::class.java)

val objectMapper get() = appCtx.getBean(ObjectMapper::class.java)

val webAppCtx get() = appCtx as WebApplicationContext

val appCtx get() = AppCtxHolder.getAppCtx()
