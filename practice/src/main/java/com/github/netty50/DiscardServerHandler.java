package com.github.netty50;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * Handles a server-side channel.
 */
public class DiscardServerHandler extends ChannelHandlerAdapter{

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
			 // Discard the received data silently.
			((ByteBuf)msg).release();
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}
	
}
