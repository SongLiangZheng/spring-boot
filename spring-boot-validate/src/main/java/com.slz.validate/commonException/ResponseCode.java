package com.slz.validate.commonException;


/**
* @className OperationEnum
* @description 返回编码
* @author tlw
* @date 2019/8/7 7:56
* @version 1.0
**/
public enum ResponseCode {
	/** 成功 */
	SUCCESS(0, "ok"),
	/** 系统错误 */
	SYS_ERROR(-1, "system error"),
	/** 登录超时 */
	LOGIN_TIMEOUT(10000000, "登录失效"),
	/** 权限异常 */
	AUTH_ERROR(-98, "unauthorized"),
	/** 业务异常 */
	SERVICE_ERROR(-2, "biz error"),
	/** 非法请求 */
	ILLEGAL_REQUEST (-1, "illegal request"),
	/** 查无此资源 */
	NOT_FOUND_RESOURCE(-1,"resource not found"),
	/** 缺少参数 */
	MISS_PARAMS(10220001, "missed parameter"),
	/** 参数错误 */
	PARAMS_ERROR(10220002, "invalid param"),
	/** 下载错误 */
	DOWNLOAD_ERROR(10220003, "download error"),

	PRIMARY_SYS_ERROR(-1000,"system is busy"),

    ;

	private final Integer val;
	private final String msg;

    ResponseCode(Integer value, String msg) {
        this.val = value;
        this.msg = msg;
    }

    public Integer val() {
        return val;
    }

    public String msg() {
        return msg;
    }


}