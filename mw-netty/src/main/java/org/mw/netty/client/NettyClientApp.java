package org.mw.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author devon.ye@foxmail.com
 * @datetime 2020/5/11 4:17 PM
 * @description
 */
public class NettyClientApp {
    private Logger LOG = LoggerFactory.getLogger(NettyClientApp.class);
    private static ChannelFuture channelFuture;

    public NettyClientApp() {

    }

    public void connect(String host, int port) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            Channel channel = socketChannel.read();

                        }
                    });
             channelFuture = bootstrap.connect(host, port).sync();
            LOG.info("init finished channelFuture:{}", channelFuture);
        } catch (Exception e) {
            LOG.error("connect fail!: ", e);
        } finally {
           // eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        NettyClientApp nettyClientApp = new NettyClientApp();
        nettyClientApp.connect("localhost", 19999);
        sendMessage();
    }

    private static void sendMessage() {
        for (int seq = 0; seq < 20; seq++) {
            int length = 16 + 40;
            ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.buffer(length);
            byteBuf.writeInt(18);
            byteBuf.writeInt(seq);
            byteBuf.writeShort(1); //cmd
            byteBuf.writeShort(1000);  //version
            byteBuf.writeBytes(new byte[20]);  //header
            byteBuf.writeInt(30); //bodyLength;
            byteBuf.writeBytes(new byte[30]);
            channelFuture.channel().writeAndFlush(byteBuf);
        }

    }
}
