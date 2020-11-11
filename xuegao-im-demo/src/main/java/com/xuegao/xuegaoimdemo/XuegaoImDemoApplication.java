package com.xuegao.xuegaoimdemo;

import com.xuegao.xuegaoimdemo.Server.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class XuegaoImDemoApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(XuegaoImDemoApplication.class, args);
        NettyServer nettyServer = configurableApplicationContext.getBean(NettyServer.class);
        nettyServer.start();
    }

}
