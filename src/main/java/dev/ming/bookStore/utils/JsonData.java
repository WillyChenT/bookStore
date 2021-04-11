package dev.ming.bookStore.utils;

public class JsonData {

    private Integer code;

    private Object data;

    private String msg;

    public JsonData(){}

    public JsonData(Integer code, Object data, String msg){
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    /**
     * 200，成功不返回數據
     * @return
     */
    public static JsonData buildSuccess(){
        return new JsonData(200,null,null);
    }

    /**
     * 200，成功返回數據
     * @param data
     * @return
     */
    public static JsonData buildSuccess(Object data){
        return new JsonData(200,data,null);
    }

    /**
     * 失敗,固定狀態碼
     * @return
     */
    public static JsonData buildError(String msg){
        return new JsonData(400,null,msg);
    }

    /**
     * 失敗,自訂義錯誤代碼和訊息
     * @param code
     * @param msg
     * @return
     */
    public static JsonData buildError(Integer code, String msg){
        return new JsonData(code,null,msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
