package org.github.runner

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.Channel
import io.netty.channel.ChannelInitializer
import io.netty.channel.kqueue.KQueueEventLoopGroup
import io.netty.channel.kqueue.KQueueServerSocketChannel
import io.netty.handler.codec.http.HttpObjectAggregator
import io.netty.handler.codec.http.HttpServerCodec
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler
import org.github.module.http.HttpServerHandler
import org.github.thread.NaiveThreadFactory

fun main() {
  val loggingHandler = LoggingHandler(LogLevel.TRACE)
  val httpServerHandler = HttpServerHandler()

  val boss = KQueueEventLoopGroup(1, NaiveThreadFactory("http-boss"))
  val worker = KQueueEventLoopGroup(0, NaiveThreadFactory("http-worker"))
  val bootstrap = ServerBootstrap()
    .group(boss, worker)
    .channel(KQueueServerSocketChannel::class.java)
    .handler(loggingHandler)
    .childHandler(object: ChannelInitializer<Channel>() {
      override fun initChannel(channel: Channel) {
        channel.pipeline()!!.apply {
          addLast(loggingHandler)
          addLast(HttpServerCodec())
          addLast(HttpObjectAggregator(512 * 1024))
          addLast(httpServerHandler)
        }
      }
    })!!

  try {
    bootstrap.bind(8000).sync().channel().closeFuture().sync()
  } catch(e: Exception) {
    e.printStackTrace()
  } finally {
    worker.shutdownGracefully().syncUninterruptibly()
    boss.shutdownGracefully().syncUninterruptibly()
  }
}