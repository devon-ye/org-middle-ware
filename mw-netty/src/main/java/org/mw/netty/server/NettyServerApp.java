package org.mw.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author devon.ye
 * @datetime 2020/5/10 9:00 PM
 * @since
 */
public class NettyServerApp {

	private static final String OS_NAME = "os.name";
	private static final String LINUX = "Linux";


	public static void main(String[] args) {
		ServerBootstrap serverBootstrap = new ServerBootstrap();


		System.out.println(System.getProperties());
		EventLoopGroup bossEventLoopGroup = null;
		EventLoopGroup workEventLoopGroup = null;
		if (LINUX.equals(System.getProperty(OS_NAME))) {
			bossEventLoopGroup = new EpollEventLoopGroup();
			workEventLoopGroup = new EpollEventLoopGroup();
		} else {
			bossEventLoopGroup = new NioEventLoopGroup();
			workEventLoopGroup = new NioEventLoopGroup();
		}

		serverBootstrap.group(bossEventLoopGroup, workEventLoopGroup)
				.channel(NioServerSocketChannel.class)
				.handler(new LoggingHandler())
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ChannelPipeline pipeline = ch.pipeline();
						pipeline.addLast(new LoggingHandler(LogLevel.DEBUG));
					}
				});
		/**
		 * socket
		 */
		ChannelFuture channelFuture = null;
		try {
			channelFuture = serverBootstrap.bind(18081).sync();
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossEventLoopGroup.shutdownGracefully();
			workEventLoopGroup.shutdownGracefully();
		}

	}
}
