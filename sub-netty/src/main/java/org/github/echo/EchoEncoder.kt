package org.github.echo

import io.netty.buffer.Unpooled.wrappedBuffer
import io.netty.channel.ChannelHandler.Sharable
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.string.StringEncoder

@Sharable
class EchoEncoder: StringEncoder(Charsets.UTF_8) {
  /** 换行分隔符. */
  private val delimiter = wrappedBuffer("\n".toByteArray()).asReadOnly()!!

  override fun encode(ctx: ChannelHandlerContext, msg: CharSequence, out: MutableList<Any>) {
    super.encode(ctx, msg, out)
    if(msg.endsWith("\n")) return
    delimiter.retain()
    out += delimiter
  }
}
