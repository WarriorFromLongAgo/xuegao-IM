package com.xuegao.xuegaoimcommon.service.interfaces;

/**
 * <br/> @PackageName：com.xuegao.xuegaoimcommon.service.interfaces
 * <br/> @ClassName：ISocketIOService
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/12/15 20:26
 */
public interface ISocketIOService {

    /**
     * 启动服务
     */
    void start();

    /**
     * 停止服务
     */
    void stop();

    /**
     * 推送信息给指定客户端
     *
     * @param userId:     客户端唯一标识
     * @param msgContent: 消息内容
     */
    void pushMessageToUser(String userId, String msgContent);
}