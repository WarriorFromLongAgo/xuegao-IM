package com.xuegao.xuegaoimwangzaiovo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br/> @PackageName：com.xuegao.xuegaoimwangzaiovo.controller
 * <br/> @ClassName：IndexController
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/11/13 16:26
 */
@RestController
@RequestMapping(path = "index")
public class IndexController {

    @RequestMapping(path = {"/", ""})
    public String index() {

        return "index";
    }
}