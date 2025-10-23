package com.seeho.exception;

import com.seeho.enums.ErrorCode;
import com.seeho.util.StringUtils;

/**
 * @author Leonpo
 * @since 2025-10-22
 */
public class BusinessException extends RuntimeException {

    private ErrorCode error;


    public BusinessException(ErrorCode error) {
        this.error = error;
    }

    public BusinessException(Exception exception, ErrorCode error) {
        super(exception);
        this.error = error;
    }

    public BusinessException(String msg) {
        super(msg);
        error = ErrorCode.DEF;
        if (StringUtils.isNotBlank(msg)) {
            error.setMessage(msg);
        }
    }
}
