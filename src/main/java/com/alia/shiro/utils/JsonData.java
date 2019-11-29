package com.alia.shiro.utils;

import java.io.Serializable;

/**
 * 功能描述: 工具类
 *
 * @author Simon
 */
public class JsonData implements Serializable {
    private static final long serialVersionUID = -6941807469463143131L;
    /**
     * 状态码
     * 200 成功, 204 无内容, 400 错误请求, 401 请求未授权, 403 禁止访问, 500 服务器内部错误, 504 网关超时
     */
    private Integer code;
    /**
     * 数据
     */
    private Object data;
    /**
     * 描述
     */
    private String msg;

    /**
     * 状态码枚举
     */
    public static enum Code {
        /**
         * 成功
         */
        SUCCESS(200),
        /**
         * 无内容
         */
        NO_CONTENT(204),
        /**
         * 错误请求
         */
        WRONG_REQUEST(400),
        /**
         * 请求未授权
         */
        REQUEST_UNAUTHORIZED(401),
        /**
         * 禁止访问
         */
        NO_ACCESS(403),
        /**
         * 服务器内部错误
         */
        INTERNAL_ERROR(500),
        /**
         * 网关超时
         */
        GATEWAY_TIMEOUT(504);

        private Integer value;

        Code(int value) {
            this.value = value;
        }

        public Integer getValue() {
            return this.value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

    }

    private JsonData(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public JsonData() {

    }

    /**
     * 成功
     *
     * @return
     */
    public static JsonData buildSuccess() {
        return new JsonData(Code.SUCCESS.value, null, null);
    }

    /**
     * 成功
     *
     * @param data 数据
     * @return
     */
    public static JsonData buildSuccess(Object data) {
        return new JsonData(Code.SUCCESS.value, data, null);
    }

    /**
     * 成功
     *
     * @param data 数据
     * @param msg  描述
     * @return
     */
    public static JsonData buildSuccess(Object data, String msg) {
        return new JsonData(Code.SUCCESS.value, data, msg);
    }

    /**
     * 成功
     *
     * @param data 数据
     * @param code 状态码
     * @return
     */
    public static JsonData buildSuccess(Object data, Integer code) {
        return new JsonData(code, data, null);
    }

    /**
     * 成功
     *
     * @param data 数据
     * @param code 状态码
     * @return
     */
    public static JsonData buildSuccess(Object data, Code code) {
        return new JsonData(code.value, data, null);
    }

    /**
     * 失败
     *
     * @return
     */
    public static JsonData buildError() {
        return new JsonData(Code.WRONG_REQUEST.value, null, "错误请求");
    }

    /**
     * 失败
     *
     * @param msg 描述
     * @return
     */
    public static JsonData buildError(String msg) {
        return new JsonData(Code.WRONG_REQUEST.value, null, msg);
    }

    /**
     * 失败
     *
     * @param msg  描述
     * @param code 状态码
     * @return
     */
    public static JsonData buildError(String msg, Integer code) {
        return new JsonData(code, null, msg);
    }

    /**
     * 失败
     *
     * @param msg  描述
     * @param code 状态码
     * @return
     */
    public static JsonData buildError(String msg, Code code) {
        return new JsonData(code.value, null, msg);
    }

    /**
     * 失败
     *
     * @param data 数据
     * @param msg  描述
     * @param code 状态码
     * @return
     */
    public static JsonData buildError(Object data, String msg, Integer code) {
        return new JsonData(code, data, msg);
    }

    /**
     * 失败
     *
     * @param data 数据
     * @param msg  描述
     * @param code 状态码
     * @return
     */
    public static JsonData buildError(Object data, String msg, Code code) {
        return new JsonData(code.value, data, msg);
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

    @Override
    public String toString() {
        return "JsonData{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
