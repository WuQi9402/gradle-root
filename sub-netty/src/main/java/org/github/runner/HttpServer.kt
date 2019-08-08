package org.github.runner

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.Channel
import io.netty.channel.ChannelInitializer
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.codec.http.HttpObjectAggregator
import io.netty.handler.codec.http.HttpServerCodec
import io.netty.handler.logging.LogLevel.*
import io.netty.handler.logging.LoggingHandler
import org.github.module.http.HttpServerHandler
import org.github.thread.NaiveThreadFactory

fun main() {
  val boss = NioEventLoopGroup(1, NaiveThreadFactory("http-boss"))
  val worker = NioEventLoopGroup(0, NaiveThreadFactory("http-worker"))

  val loggingHandler = LoggingHandler(TRACE)
  val httpServerHandler = HttpServerHandler()

  ServerBootstrap()
    .group(boss, worker)
    .channel(NioServerSocketChannel::class.java)
    .handler(loggingHandler)
    .childHandler(object: ChannelInitializer<Channel>() {
      override fun initChannel(ch: Channel) {
        ch.pipeline().apply {
          addLast(loggingHandler)
          addLast(HttpServerCodec())
          addLast(HttpObjectAggregator(512 * 1024))
          addLast(httpServerHandler)
        }
      }
    })
    .bind(9000)
    .sync()
    .channel()
    .closeFuture()
    .addListener { boss.shutdownGracefully();worker.shutdownGracefully() }
}
