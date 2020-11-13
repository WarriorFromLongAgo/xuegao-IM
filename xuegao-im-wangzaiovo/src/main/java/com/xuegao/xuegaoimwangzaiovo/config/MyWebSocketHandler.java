package com.xuegao.xuegaoimwangzaiovo.config;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.ssl.SslHandler;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * <br/> @PackageName：com.xuegao.xuegaoimwangzaiovo.config
 * <br/> @ClassName：MyWebSocketHandler
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/11/13 16:45
 */
@Component
@ChannelHandler.Sharable
public class MyWebSocketHandler extends ChannelInboundHandlerAdapter {

    private static final String WEB_SOCKET = "websocket";
    private static final String UP_GRADE = "Upgrade";
    private static final Integer HTTP_STATUS_SUCCESS = 200;
    private static final String HTTP_FAIL_MESSAGE = "请求异常";
    private static final String WEB_SOCKET_FACTORY = "ws://127.0.0.1:8888/websocket";

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        }
    }

    // 王仔 OvO
    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) {
        // 如果请求 不是成功的
        if (!fullHttpRequest.decoderResult().isSuccess()
                // 并且 不是一个 web socket 的请求
                || WEB_SOCKET.equalsIgnoreCase(fullHttpRequest.headers().get(UP_GRADE))) {
            DefaultFullHttpResponse fullHttpResponse =
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
            // 如果请求的返回，不是 200
            if (!HTTP_STATUS_SUCCESS.equals(fullHttpResponse.status().code())) {
                ByteBuf byteBuf = Unpooled.copiedBuffer(HTTP_FAIL_MESSAGE, StandardCharsets.UTF_8);
                fullHttpResponse.content().writeBytes(byteBuf);
                byteBuf.release();
            }
            ctx.writeAndFlush(fullHttpResponse);
            return;
        }
        WebSocketServerHandshakerFactory webSocketServerHandshakerFactory =
                new WebSocketServerHandshakerFactory(WEB_SOCKET_FACTORY, null, false);

    }

    // private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) {
    //     // 如果请求 不是成功的
    //     if (!fullHttpRequest.decoderResult().isSuccess()
    //             // 并且 不是一个 web socket 的请求
    //             || WEB_SOCKET.equalsIgnoreCase(fullHttpRequest.headers().get(UP_GRADE))) {
    //         DefaultFullHttpResponse fullHttpResponse =
    //                 new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
    //         // 如果请求的返回，不是 200
    //         if (!HTTP_STATUS_SUCCESS.equals(fullHttpResponse.status().code())) {
    //             ByteBuf byteBuf = Unpooled.copiedBuffer(HTTP_FAIL_MESSAGE, StandardCharsets.UTF_8);
    //             fullHttpResponse.content().writeBytes(byteBuf);
    //             byteBuf.release();
    //         }
    //         ctx.writeAndFlush(fullHttpResponse);
    //     }
    // }

    private static void sendHttpResponse(ChannelHandlerContext ctx,
                                         FullHttpRequest fullHttpRequest,
                                         FullHttpResponse fullHttpResponse) {
        // Generate an error page if response getStatus code is not OK (200).
        if (!HTTP_STATUS_SUCCESS.equals(fullHttpResponse.status().code())) {
            ByteBuf byteBuf = Unpooled.copiedBuffer(fullHttpResponse.status().toString(), StandardCharsets.UTF_8);
            fullHttpResponse.content().writeBytes(byteBuf);
            byteBuf.release();
            // HttpHeaders.setContentLength(fullHttpResponse, fullHttpResponse.content().readableBytes());
            HttpUtil.setContentLength(fullHttpResponse, fullHttpResponse.content().readableBytes());
        }

        // Send the response and close the connection if necessary.
        ChannelFuture channelFuture = ctx.channel().writeAndFlush(fullHttpResponse);
        // if (!HttpHeaders.isKeepAlive(fullHttpRequest) || fullHttpResponse.getStatus().code() != 200) {
        if (!HttpUtil.isKeepAlive(fullHttpRequest) || !HTTP_STATUS_SUCCESS.equals(fullHttpResponse.status().code())) {
            channelFuture.addListener(ChannelFutureListener.CLOSE);
        }
    }

    private static String getWebSocketLocation(ChannelPipeline channelPipeline, HttpRequest httpRequest, String path) {
        String protocol = "ws";
        if (channelPipeline.get(SslHandler.class) != null) {
            // SSL in use so use Secure WebSockets
            protocol = "wss";
        }
        return protocol + "://" + httpRequest.headers().get(HttpHeaderNames.HOST) + path;
    }
}