package org.github.config

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption.SO_BACKLOG
import io.netty.channel.ChannelOption.SO_KEEPALIVE
import io.netty.channel.ChannelOption.SO_REUSEADDR
import io.netty.channel.ChannelOption.TCP_NODELAY
import io.netty.channel.epoll.EpollEventLoopGroup
import io.netty.channel.epoll.EpollServerSocketChannel
import io.netty.channel.epoll.EpollSocketChannel
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.LineBasedFrameDecoder
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder
import io.netty.handler.logging.LoggingHandler
import org.github.netty.handler.StringServerChannelHandler
import org.github.netty.server.ServerChannelHolder
import org.github.netty.server.ServerSocketLoggingHandler
import org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class NettyConfig {
  @Bean
  fun epollServerChannelHolder(props: NettyPortProperties): ServerChannelHolder {
    val boss = EpollEventLoopGroup(1)
    val worker = EpollEventLoopGroup()
    return ServerBootstrap()
      .group(boss, worker)
      .channel(EpollServerSocketChannel::class.java)
      .option(SO_BACKLOG, 1024)
      .option(SO_REUSEADDR, true)
      .handler(ServerSocketLoggingHandler("Netty-epoll"))
      .childHandler(object: ChannelInitializer<EpollSocketChannel>() {
        override fun initChannel(channel: EpollSocketChannel) {
          channel.pipeline()!!.apply {
            addLast(loggingHandler())
            addLast(lineBasedFrameDecoder())
            addLast(stringDecoder())
            addLast(stringEncoder())
            addLast(stringServerChannelHandler())
          }
        }
      })
      .childOption(SO_KEEPALIVE, true)
      .childOption(TCP_NODELAY, true)
      .bind(props.epoll)
      .sync()
      .channel()
      .closeFuture()
      .addListener { boss.shutdownGracefully(); worker.shutdownGracefully() }
      .let { ServerChannelHolder(it.channel()) }
  }

  @Bean
  fun nioServerChannelHolder(props: NettyPortProperties): ServerChannelHolder {
    val boss = NioEventLoopGroup(1)
    val worker = NioEventLoopGroup()
    return ServerBootstrap()
      .group(boss, worker)
      .channel(NioServerSocketChannel::class.java)
      .option(SO_BACKLOG, 1024)
      .option(SO_REUSEADDR, true)
      .handler(ServerSocketLoggingHandler("Netty-nio"))
      .childHandler(object: ChannelInitializer<NioSocketChannel>() {
        override fun initChannel(channel: NioSocketChannel) {
          channel.pipeline()!!.apply {
            addLast(loggingHandler())
            addLast(lineBasedFrameDecoder())
            addLast(stringDecoder())
            addLast(stringEncoder())
            addLast(stringServerChannelHandler())
          }
        }
      })
      .childOption(SO_KEEPALIVE, true)
      .childOption(TCP_NODELAY, true)
      .bind(props.nio)
      .sync()
      .channel()
      .closeFuture()
      .addListener { boss.shutdownGracefully(); worker.shutdownGracefully() }
      .let { ServerChannelHolder(it.channel()) }
  }

  @Scope(SCOPE_PROTOTYPE)
  @Bean
  fun lineBasedFrameDecoder() = LineBasedFrameDecoder(1024)

  @Bean
  fun stringDecoder() = StringDecoder()

  @Bean
  fun stringEncoder() = StringEncoder()

  @Bean
  fun loggingHandler() = LoggingHandler()

  @Bean
  fun stringServerChannelHandler() = StringServerChannelHandler()
}
