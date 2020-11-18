package com.xuegao.xuegaoimclient.config;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * <br/> @PackageName：com.xuegao.xuegaoimclient.config
 * <br/> @ClassName：ClientHandler
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/11/18 19:26
 */
public class ClientHandler extends SimpleChannelInboundHandler<String> {
    private Logger log = LoggerFactory.getLogger(ClientHandler.class);

    public static List<Channel> channelList = new LinkedList<>();

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        for (Channel channel : channelList) {
            channel.writeAndFlush(ctx.channel().remoteAddress().toString() + " 已经进入聊天室");
        }
        channelList.add(ctx.channel());
    }

    // 通道就绪
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelList.add(channel);
        log.error("client：{} 上线了", channel.remoteAddress().toString());

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        channelList.remove(ctx.channel());
        for (Channel channel : channelList) {
            channel.writeAndFlush(ctx.channel().remoteAddress() + " 离开了聊天室");
        }
    }

    // 通道未就绪
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        channelList.remove(ctx.channel());
        log.error("client：{} 离线了", ctx.channel().remoteAddress().toString());

    }

    // 读取数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String content) throws Exception {
        // myself 自己
        Channel myselfChannel = ctx.channel();

        for (Channel channel : channelList) {
            //不是刚刚进来的
            if (channel != myselfChannel) {
                channel.writeAndFlush(ctx.channel().remoteAddress() + "：" + content);
            } else {
                channel.writeAndFlush("我自己：" + content);
            }
        }
    }
}