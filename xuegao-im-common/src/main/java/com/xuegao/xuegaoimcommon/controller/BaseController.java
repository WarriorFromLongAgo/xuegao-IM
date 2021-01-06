package com.xuegao.xuegaoimcommon.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xuegao.xuegaoimcommon.constant.common.WrappedResponse;
import com.xuegao.xuegaoimcommon.utils.ServletUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController<T> {

    /**
     * 获取request
     */
    public HttpServletRequest getRequest() {
        return ServletUtils.getRequest();
    }

    /**
     * 获取response
     */
    public HttpServletResponse getResponse() {
        return ServletUtils.getResponse();
    }

    /**
     * 获取session
     */
    public HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 返回成功
     */
    public WrappedResponse<T> success() {
        return WrappedResponse.success();
    }

    /**
     * 返回失败消息
     */
    public WrappedResponse<T> fail() {
        return WrappedResponse.fail();
    }

    /**
     * 返回成功消息
     */
    public WrappedResponse<T> success(String message) {
        return WrappedResponse.success(message);
    }

    /**
     * 返回失败消息
     */
    public WrappedResponse<T> fail(T data) {
        return WrappedResponse.fail(data);
    }

    /**
     * 返回成功消息
     */
    public WrappedResponse<T> success(T data) {
        return WrappedResponse.success(data);
    }

    /**
     * 返回失败消息
     */
    public WrappedResponse<T> fail(String message) {
        return WrappedResponse.fail(message);
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return StringUtils.format("redirect:{}", url);
    }

}