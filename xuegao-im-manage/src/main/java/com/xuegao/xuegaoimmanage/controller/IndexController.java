package com.xuegao.xuegaoimmanage.controller;

import com.xuegao.xuegaoimcommon.constant.common.WrappedResponse;
import com.xuegao.xuegaoimcommon.controller.BaseController;
import com.xuegao.xuegaoimmanage.domain.doo.SysUserinfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * <br/> @PackageName：com.xuegao.xuegaoimmanage.controller
 * <br/> @ClassName：IndexController
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/11/21 12:15
 */
@RestController
public class IndexController<T> extends BaseController<T> {
    private final Logger log = LoggerFactory.getLogger(IndexController.class);

    @GetMapping(path = {"/", "/index"})
    public WrappedResponse<String> uploadFile() {
        log.info("index 访问成功");
        return WrappedResponse.success("index");
    }

    @CrossOrigin
    @PostMapping(path = "/post1")
    public WrappedResponse<T> post1Test(@RequestBody SysUserinfo sysUserinfo) {
        return success("post1 = " + sysUserinfo);
    }
}