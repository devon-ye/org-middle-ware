package org.mw.netty.serializer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

/**
 * @author devon.ye@foxmail.com
 * @datetime 2020/5/11 4:17 PM
 * @description
 */
public class MessageCode extends ByteToMessageCodec<Byte[]> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Byte[] msg, ByteBuf out) throws Exception {

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

    }
}
