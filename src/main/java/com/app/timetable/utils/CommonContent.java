package com.app.timetable.utils;

/**
 * judithchen
 * 2019/4/1
 * Description：
 **/
public class CommonContent {

//    public static final String FILE_PATH = "/usr/share/nginx/images/";

    public static final String FILE_PATH = "D:\\documents\\images\\";

    public static final String IMAGE_URL = "https://www.meitang.club/";

    public static final String APP_ID = "wxb0f5aa5c4ecc6b23";

    public static final String APP_SECRET = "ba05904202dcd87609415795ab9a54c3";

    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    public static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    public static final String GET_USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
}
