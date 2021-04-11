package dev.ming.bookStore.exception;

/**
 * 自訂異常類
 */
public class BasicException extends RuntimeException{

    private Integer code;

    private String msg;

    public BasicException(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
