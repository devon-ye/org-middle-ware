package org.mw.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * @author devon.ye@foxmail.com
 * @datetime 2020/5/11 4:17 PM
 * @description
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        String uri = request.uri();





        HttpResponse httpResponse = new DefaultHttpResponse(request.protocolVersion(), HttpResponseStatus.OK);


        String contentType="";
        httpResponse.headers().set(HttpHeaders.Names.CONTENT_TYPE,contentType+"charset=utf-8;");
        HttpHeaders.isKeepAlive(request);

    }
}
