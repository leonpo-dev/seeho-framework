package com.seeho.service.test.service.enums;

import com.seeho.service.test.service.exception.ExceptionCodeType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode implements ExceptionCodeType {

    DEF("1000000", "系统异常"),
    HTTP_ERROR("1000001", "http_request_is_exceptional"),
    JSON_CONVERSION("1000002", "json_conversion_exception"),
    BEAN_COPY("1000003", "bean_copy_exception"),

    ;

    private String code;
    private String message;

   public void setMessage(String message) {
        this.message = message;
    }
}
