package com.seeho.enums;

import com.seeho.exception.ExceptionCodeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum ErrorCode implements ExceptionCodeType {

    DEF("1000000", "系统异常"),
    HTTP_ERROR("1000001", "http_request_is_exceptional"),

    ;

    private String code;
    private String message;

   public void setMessage(String message) {
        this.message = message;
    }
}
