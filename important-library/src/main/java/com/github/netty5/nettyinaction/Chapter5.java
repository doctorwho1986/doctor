package com.github.netty5.nettyinaction;

import java.nio.charset.StandardCharsets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import org.junit.Test;

public class Chapter5 {

	@Test
	public void test_1(){
		ByteBuf byteBuf = Unpooled.buffer();
		System.out.println("capacity :" + byteBuf.capacity());
		System.out.println("hasArray :" +byteBuf.hasArray());
		System.out.println("hashCode :" +byteBuf.hashCode());
		System.out.println("hasMemoryAddress :" +byteBuf.hasMemoryAddress());
		System.out.println("isDirect :" +byteBuf.isDirect());
		System.out.println("maxCapacity :" +byteBuf.maxCapacity());
		System.out.println("readableBytes :" +byteBuf.readableBytes());
		System.out.println("refCnt :" +byteBuf.refCnt());
		System.out.println("writerIndex :" +byteBuf.writerIndex());
		System.out.println("readerIndex :" +byteBuf.readerIndex());
	}
	
	@Test
	public void test_2(){
		ByteBuf byteBuf = Unpooled.buffer(888);
		System.out.println("capacity :" + byteBuf.capacity());
		System.out.println("hasArray :" +byteBuf.hasArray());
		System.out.println("hashCode :" +byteBuf.hashCode());
		System.out.println("hasMemoryAddress :" +byteBuf.hasMemoryAddress());
		System.out.println("isDirect :" +byteBuf.isDirect());
		System.out.println("maxCapacity :" +byteBuf.maxCapacity());
		System.out.println("readableBytes :" +byteBuf.readableBytes());
		System.out.println("refCnt :" +byteBuf.refCnt());
		System.out.println("writerIndex :" +byteBuf.writerIndex());
		System.out.println("readerIndex :" +byteBuf.readerIndex());
	}
	
	
	@Test
	public void test_3(){
		ByteBuf byteBuf = Unpooled.directBuffer(888, 1888);
		System.out.println("capacity :" + byteBuf.capacity());
		System.out.println("hasArray :" +byteBuf.hasArray());
		System.out.println("hashCode :" +byteBuf.hashCode());
		System.out.println("hasMemoryAddress :" +byteBuf.hasMemoryAddress());
		System.out.println("isDirect :" +byteBuf.isDirect());
		System.out.println("maxCapacity :" +byteBuf.maxCapacity());
		System.out.println("readableBytes :" +byteBuf.readableBytes());
		System.out.println("refCnt :" +byteBuf.refCnt());
		System.out.println("writerIndex :" +byteBuf.writerIndex());
		System.out.println("readerIndex :" +byteBuf.readerIndex());
	}
	
	
	@Test
	public void test_wrappedBuffer(){
		String message = "who am I";
		ByteBuf byteBuf = Unpooled.wrappedBuffer(message.getBytes(StandardCharsets.UTF_8));
		System.out.println("capacity :" + byteBuf.capacity());
		System.out.println("hasArray :" +byteBuf.hasArray());
		System.out.println("hashCode :" +byteBuf.hashCode());
		System.out.println("hasMemoryAddress :" +byteBuf.hasMemoryAddress());
		System.out.println("isDirect :" +byteBuf.isDirect());
		System.out.println("maxCapacity :" +byteBuf.maxCapacity());
		System.out.println("readableBytes :" +byteBuf.readableBytes());
		System.out.println("refCnt :" +byteBuf.refCnt());
		System.out.println("writerIndex :" +byteBuf.writerIndex());
		System.out.println("readerIndex :" +byteBuf.readerIndex());
		System.out.println("isWritable :" +byteBuf.isWritable());
		
		byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
		ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(bytes);
		System.out.println(wrappedBuffer.toString(StandardCharsets.UTF_8));
		bytes[0] = Byte.parseByte("65");
		System.out.println(wrappedBuffer.toString(StandardCharsets.UTF_8));
		System.out.println("refCnt :" +byteBuf.refCnt());

	}
	
	@Test
	public void test_wrappedBuffers(){
		CompositeByteBuf wrappedBuffer = (CompositeByteBuf) Unpooled.wrappedBuffer("ss".getBytes(StandardCharsets.UTF_8),"do".getBytes(StandardCharsets.UTF_8));
		System.out.println(wrappedBuffer.capacity());
		System.out.println(wrappedBuffer.hasArray());
		System.out.println(wrappedBuffer.numComponents());
		wrappedBuffer.writeBytes(" who am i".getBytes(StandardCharsets.UTF_8));
		System.out.println(wrappedBuffer.toString(StandardCharsets.UTF_8));
		System.out.println(wrappedBuffer.toString(StandardCharsets.UTF_8));
		byte[] dst = new byte[wrappedBuffer.readableBytes()];
		wrappedBuffer.getBytes(0, dst);
		
		System.out.println(new String(dst,StandardCharsets.UTF_8));
	}
}
