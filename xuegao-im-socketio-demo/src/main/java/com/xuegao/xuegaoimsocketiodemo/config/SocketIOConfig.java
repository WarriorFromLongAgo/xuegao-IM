package com.xuegao.xuegaoimsocketiodemo.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> socket.io配置类 </p>
 *
 * @author : zhengqing
 * @description :
 * @date : 2020/2/7 20:31
 */
@Configuration
public class SocketIOConfig {

    @Value("${socketio.host}")
    private String host;

    @Value("${socketio.port}")
    private Integer port;

    @Value("${socketio.bossCount}")
    private Integer bossCount;

    @Value("${socketio.workCount}")
    private Integer workCount;

    @Value("${socketio.allowCustomRequests}")
    private Boolean allowCustomRequests;

    @Value("${socketio.upgradeTimeout}")
    private Integer upgradeTimeout;

    @Value("${socketio.pingTimeout}")
    private Integer pingTimeout;

    @Value("${socketio.pingInterval}")
    private Integer pingInterval;

    @Bean
    public SocketIOServer socketIOServer() {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setSocketConfig(socketConfig);
        config.setHostname(host);
        config.setPort(port);
        config.setBossThreads(bossCount);
        config.setWorkerThreads(workCount);
        config.setAllowCustomRequests(allowCustomRequests);
        config.setUpgradeTimeout(upgradeTimeout);
        config.setPingTimeout(pingTimeout);
        config.setPingInterval(pingInterval);
        return new SocketIOServer(config);
    }

}
