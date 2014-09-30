package com.github.netty5.nettyinaction;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Chapter2 {

	@Test
	public void test_echo_server(){
		echoServer(1055);
	}
	
	@Test
	public void test_echo_client()
	{
		echoClient("localhost", 1055);
	}
	
	private void echoServer(int port){
		NioEventLoopGroup bossGroup = new NioEventLoopGroup();
		NioEventLoopGroup workGroup = new NioEventLoopGroup();
		ServerBootstrap bootstrap = new ServerBootstrap();

		bootstrap.group(bossGroup,workGroup)
				 .channel(NioServerSocketChannel.class)
				 .localAddress(new InetSocketAddress(port))
				 .childHandler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new  EchoServerHandler());
					}
					 
				}).option(ChannelOption.SO_BACKLOG, 128)
				.childOption(ChannelOption.SO_KEEPALIVE, true);
		
		try {
			ChannelFuture channelFuture = bootstrap.bind().sync();
			System.out.println("server stated and listen on " + channelFuture.channel().localAddress());
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			
		}finally{
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}
	
	
	private void echoClient(String host, int port){
		NioEventLoopGroup group = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(group)
				 .channel(NioSocketChannel.class)
				 .remoteAddress(host, port)
				 .handler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new  EchoClientHandler());
					}
					 
				});
		
		try {
			ChannelFuture channelFuture = bootstrap.connect().sync();
			System.out.println("client connect : " + host);
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			
		}finally{
			group.shutdownGracefully();
		}
	}
}

@Sharable
class EchoServerHandler extends ChannelHandlerAdapter{

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ctx.write(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	
}

@Sharable
class EchoClientHandler extends ChannelHandlerAdapter{
	private static final Logger log = LoggerFactory.getLogger(EchoClientHandler.class);
	private String first = "hello netty5";
	private ByteBuf buffer = Unpooled.copiedBuffer(first.getBytes(StandardCharsets.UTF_8));
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		String hexDump = buf.toString(StandardCharsets.UTF_8);
		ctx.write(msg);
		log.info("{client read :'{}'}",hexDump);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(buffer);
		log.info("{channelActive write message:'{}'}",first);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		log.error("{exceptionCaught :'{}'}",cause.getMessage());
		ctx.close();
	}
	
}