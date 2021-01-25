package com.xuegao.imsocketiodemo1.com.xuegao.im.domain;

import org.springframework.stereotype.Component;

/**
 * <br/> @PackageName：com.xuegao.imsocketiodemo1.com.xuegao.im.domain
 * <br/> @ClassName：MessageInfo
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/01/24 16:24
 */
@Component
public class MessageInfo {

    String msgContent;

    public String getMsgContent() {
        return this.msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    @Override
    public String toString() {
        return "MessageInfo{" +
                "msgContent='" + msgContent + '\'' +
                '}';
    }
}