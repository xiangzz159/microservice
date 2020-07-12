package com.study.demo.common.constant

import com.study.frame.base.BaseResult;
/**
 * 系统常量枚举类
 */
class ApiResult external BaseResult {
    public ApiResult(int code, String message, Object data) {
        super(code, message, data);
    }

    public ApiResult(ApiResultConstant apiResultConstant, Object data) {

    }