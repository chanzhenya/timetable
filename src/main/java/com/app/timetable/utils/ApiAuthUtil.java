package com.app.timetable.utils;

import com.alibaba.fastjson.JSONObject;
import com.app.timetable.entity.SysUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import redis.clients.jedis.Jedis;

/**
 * @author Judith
 * @date 2019/4/29 14:44
 * @description 接口安全访问工具类
 */
public class ApiAuthUtil {

    public static String getToken(SysUser sysUser, String session_key) {
        String token = "";
        token = JWT.create().withAudience(sysUser.getId(),sysUser.getUserType().toString())
                .sign(Algorithm.HMAC256(session_key));
        Jedis jedis = RedisUtil.getJedis(sysUser.getId());
        JSONObject userJson = new JSONObject();
        userJson.put("openId",sysUser.getOpenId());
        userJson.put("session_key",session_key);
        userJson.put("userType", sysUser.getUserType());
        jedis.set(sysUser.getId(),userJson.toJSONString());
        jedis.expire(sysUser.getId(),CommonContent.EXPIRE_WEEK); // token有效时间
        jedis.close();
        return token;
    }

    /**
     * 设置key的有效时间
     * @param key
     * @param second
     */
    public static void expire(String key, int second) {
        Jedis jedis = RedisUtil.getJedis(key);
        jedis.expire(key,second);
        jedis.close();
    }
}
