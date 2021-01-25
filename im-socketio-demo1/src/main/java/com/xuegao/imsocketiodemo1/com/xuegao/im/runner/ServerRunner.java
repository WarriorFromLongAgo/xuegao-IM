package com.xuegao.imsocketiodemo1.com.xuegao.im.runner;

import com.corundumstudio.socketio.SocketIOServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <br/> @PackageName：com.xuegao.imsocketiodemo1.com.xuegao.im.runner
 * <br/> @ClassName：ServerRunner
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/01/24 16:22
 */
@Component
@Order(value=1)
public class ServerRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(ServerRunner.class);

    private final SocketIOServer server;

    @Autowired
    public ServerRunner(SocketIOServer server) {
        this.server = server;
    }

    @Override
    public void run(String... args) throws Exception {
        server.start();
        log.info("socket.io启动成功！");
    }
}