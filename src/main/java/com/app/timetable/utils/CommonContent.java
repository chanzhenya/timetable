package com.app.timetable.utils;

/**
 * judithchen
 * 2019/4/1
 * Description：
 **/
public class CommonContent {

    public static final String TOKEN_KEY = "TIMETABLE_TOKEN";

    // token 有效时间
    public static final long LOGIN_EXPIRE_TIME = 60*60;

    public static final String FILE_PATH = "/usr/share/nginx/images/";

//    public static final String FILE_PATH = "D:\\documents\\images\\";

    public static final String IMAGE_URL = "https://www.meitang.club/";

    public static final int EXPIRE_WEEK = 604800;

    public static final String APP_ID = "wxb0f5aa5c4ecc6b23";

    public static final String APP_SECRET = "ba05904202dcd87609415795ab9a54c3";

    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
}
