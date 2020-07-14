package com.study.api.server.controller;

import com.study.api.rpc.api.ApiService;
import com.study.frame.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author：xiangguangye
 * @Desc：后台controller
 * @Date：2020/7/13 13:33
 */
@Api(value="test")
@Controller
public class TestController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private ApiService apiService;

    @ApiOperation(value = "test")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test(ModelMap modelMap) {
        return apiService.hello("world");
    }

}
