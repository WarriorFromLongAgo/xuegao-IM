package com.xuegao.imsocketioserver2.chat;

/**
 * <br/> @PackageName：com.xuegao.imsocketioserver2.chat
 * <br/> @ClassName：ChatMessage
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/01/24 17:20
 */
public class ChatMessage {
    private String userName;
    private String message;

    public ChatMessage() {
    }

    public ChatMessage(String userName, String message) {
        super();
        this.userName = userName;
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "userName='" + userName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}