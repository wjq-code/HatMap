package com.llxy.execption;

/**
 * ������: SSCExecption
 * ������:
 *
 * @author �佨ǫ
 * @Time 2019/1/7 17:19
 */
public class SSCExecption extends RuntimeException{
    public SSCExecption() {
    }

    public SSCExecption(String message) {
        super(message);
    }

    public SSCExecption(String message, Throwable cause) {
        super(message, cause);
    }

    public SSCExecption(Throwable cause) {
        super(cause);
    }

    public SSCExecption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
