package org.mw.netty.serializer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author devon.ye@foxmail.com
 * @datetime 2020/5/11 4:17 PM
 * @description
 */

public class MessageDecode extends ByteToMessageDecoder {
    private static final Logger LOG = LoggerFactory.getLogger(MessageDecode.class);

    private static final int PROTOCOL_HEADER_LENGTH = 20;
    private static final int PROTOCOL_BODY_MAX_LENGTH = 1024 * 1024;


    /**
     * 黏包 拆包处理
     *
     * @param ctx
     * @param byteBuf
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        int length = byteBuf.readableBytes();
        if (length <= PROTOCOL_HEADER_LENGTH) {
            return;
        }

        if (length > PROTOCOL_BODY_MAX_LENGTH) {
            return;
        }

        int headerLength = byteBuf.readInt();
        long seq =byteBuf.readLong();
        int cmdType =byteBuf.readByte();
        int version =byteBuf.readByte();
        ByteBuf  hedaerData = byteBuf.readBytes(new byte[headerLength-10]);
        int bodyLength = byteBuf.readInt();
        ByteBuf  body = byteBuf.readBytes(new byte[bodyLength]);
         out.add(byteBuf.readerIndex(PROTOCOL_BODY_MAX_LENGTH + bodyLength));
    }
}
