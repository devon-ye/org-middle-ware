package org.mw.netty.client;

import io.netty.bootstrap.Bootstrap;
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
                          Channel channel =  socketChannel.read();

                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            LOG.error("connect fail!: ", e);
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        NettyClientApp nettyClientApp = new NettyClientApp();
        nettyClientApp.connect("localhost", 18081);
    }
}
