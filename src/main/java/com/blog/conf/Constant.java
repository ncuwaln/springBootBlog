package com.blog.conf;

/**
 * Created by Administrator on 2017/2/4.
 */
public class Constant {
    /**
     * 数据请求返回码
     */
    public static final int RESCODE_SUCCESS = 1000;				//成功
    public static final int RESCODE_SUCCESS_MSG = 1001;			//成功(有返回信息)
    public static final int RESCODE_EXCEPTION = 1002;			//请求抛出异常
    public static final int RESCODE_NOLOGIN = 1003;				//未登陆状态
    public static final int RESCODE_NOEXIST = 1004;				//查询结果为空
    public static final int RESCODE_NOAUTH = 1005;				//无操作权限

    /**
     * jwt
     */
    public static final String JWT_ID = "jwt";
    public static final String JWT_SECRET = "\\xfaM\\xd5#)\\xf7\\x1b\\x0c\\xb6]wi\\x13\\xef\\x12P\\x05\\xd4\\xe0\\xdd\\xe5\\x0bc\\x9c";
    public static final int JWT_TTL = 60*60*1000;  //millisecond
    public static final int JWT_REFRESH_INTERVAL = 55*60*1000;  //millisecond
    public static final int JWT_REFRESH_TTL = 12*60*60*1000;  //millisecond

}
