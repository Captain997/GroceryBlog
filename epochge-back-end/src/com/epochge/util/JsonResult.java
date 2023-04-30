package com.epochge.util;

/**
 *
 */
public class JsonResult<T> {
    private T data;
    private  Integer code;
    private String msg;
    /**
     * code	Integer	  0成功 1失败  2 其他原因
     * 若没有数据返回，默认状态码为0，提示信息为：操作失败！
     */
    public JsonResult() {
        this.code = 0;
        this.msg = "操作成功！";
    }
    /**
     * 若没有数据返回，可以人为指定状态码和提示信息
     * @param code
     * @param msg
     */
    public JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public JsonResult(Integer code, String msg,T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    /**
     * 有数据返回时，状态码为0，默认提示信息为：操作成功！
     * @param data
     */
//    public JsonResult(T data) {
//        this.data = data;
//        this.code = 0;
//        this.msg = "操作成功！";
//    }
    /**
     * 有数据返回，状态码为0，人为指定提示信息
     * @param data
     * @param msg
     */
    public JsonResult(T data, String msg) {
        this.data = data;
        this.code = 0;
        this.msg = msg;
    }

    public JsonResult(Integer code) {
        this.code = code;
        this.msg = code>0 ? "操作成功":"操作失败";
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
