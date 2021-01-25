package com.xuegao.imsocketiodemo1.com.xuegao.im.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.xuegao.imsocketiodemo1.com.xuegao.im.domain.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <br/> @PackageName：com.xuegao.imsocketiodemo1.com.xuegao.im.handler
 * <br/> @ClassName：MessageEventHandler
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/01/24 16:25
 */
@Component
public class MessageEventHandler {

    private final SocketIOServer socketIoServer;
    static ArrayList<UUID> listClient = new ArrayList<UUID>();
    static final int limitSeconds = 60;
    //线程安全的map
    private ConcurrentMap<String, SocketIOClient> webSocketMap = new ConcurrentHashMap<>();

    public ConcurrentMap<String, SocketIOClient> getWebSocketMap() {
        return webSocketMap;
    }

    @Autowired
    public MessageEventHandler(SocketIOServer server) {
        socketIoServer = server;
    }

    /**
     * 客户端连接的时候触发，前端js触发：socket = io.connect("http://192.168.9.209:9092");
     *
     * @param client
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        String mac = client.getHandshakeData().getSingleUrlParam("mac");
        listClient.add(client.getSessionId());
        //以mac地址为key,SocketIOClient 为value存入map,后续可以指定mac地址向客户端发送消息
        webSocketMap.put(mac, client);
        //socketIoServer.getClient(client.getSessionId()).sendEvent("message", "back data");
        sendBuyLogEvent();
        System.out.println("客户端:" + client.getSessionId() + "已连接,mac=" + mac);
    }

    /**
     * 客户端关闭连接时触发：前端js触发：socket.disconnect();
     *
     * @param client
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        System.out.println("客户端:" + client.getSessionId() + "断开连接");
    }

    /**
     * 自定义消息事件，客户端js触发：socket.emit('messageevent', {msgContent: msg}); 时触发
     * 前端js的 socket.emit("事件名","参数数据")方法，是触发后端自定义消息事件的时候使用的,
     * 前端js的 socket.on("事件名",匿名函数(服务器向客户端发送的数据))为监听服务器端的事件
     *
     * @param client  　客户端信息
     * @param request 请求信息
     * @param data    　客户端发送数据{msgContent: msg}
     */
    @OnEvent(value = "messageevent")
    public void onEvent(SocketIOClient client, AckRequest request, MessageInfo data) {
        System.out.println("发来消息：" + data);
        //服务器端向该客户端发送消息
        //socketIoServer.getClient(client.getSessionId()).sendEvent("messageevent", "你好 data");
        client.sendEvent("messageevent", "我是服务器都安发送的信息");
    }

    public void sendBuyLogEvent() {   //这里就是向客户端推消息了
        //String dateTime = new DateTime().toString("hh:mm:ss");

        for (UUID clientId : listClient) {
            if (socketIoServer.getClient(clientId) == null) {
                continue;
            }
            SocketIOClient client = socketIoServer.getClient(clientId);
            client.sendEvent("enewbuy", "当前时间", 1);
        }

    }

}