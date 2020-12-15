package com.xuegao.xuegaoimcommon;

import io.socket.client.IO;
import io.socket.client.Socket;
import org.assertj.core.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <br/> @PackageName：com.xuegao.xuegaoimcommon
 * <br/> @ClassName：SocketIOClientLaunch
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/12/15 20:29
 */
public class SocketIOClientLaunch {
    private static final Logger log = LoggerFactory.getLogger(SocketIOClientLaunch.class);

    public static void main(String[] args) {
        // 服务端socket.io连接通信地址
        String url = "http://127.0.0.1:8888";
        try {
            IO.Options options = new IO.Options();
            options.transports = new String[]{"websocket"};
            options.reconnectionAttempts = 2;
            // 失败重连的时间间隔
            options.reconnectionDelay = 1000;
            // 连接超时时间(ms)
            options.timeout = 500;
            // userId: 唯一标识 传给服务端存储
            final Socket socket = IO.socket(url + "?userId=1", options);

            socket.on(Socket.EVENT_CONNECT, args1 -> socket.send("hello..."));

            // 自定义事件`connected` -> 接收服务端成功连接消息
            socket.on("connected", objects -> log.debug("服务端:" + objects[0].toString()));

            // 自定义事件`push_data_event` -> 接收服务端消息
            socket.on("push_data_event", objects -> log.debug("服务端:" + objects[0].toString()));

            // 自定义事件`myBroadcast` -> 接收服务端广播消息
            socket.on("myBroadcast", objects -> log.debug("服务端：" + objects[0].toString()));

            socket.connect();

            while (true) {
                Thread.sleep(3000);
                // 自定义事件`push_data_event` -> 向服务端发送消息
                socket.emit("push_data_event", "发送数据 " + DateUtil.now());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}