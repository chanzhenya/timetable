package com.app.timetable.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSONObject;
import com.app.timetable.service.WeChatService;
import com.app.timetable.utils.CommonContent;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Judith
 * @date 2019/7/30 15:07
 * @description
 */
@Service
public class WeChatServiceImpl implements WeChatService {

    Log log = LogFactory.get();

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public String getAccessToken() {
        RMap<String,Object> map = redissonClient.getMap("wechat:auth");
        String accessToken = MapUtil.getStr(map,"access_token");
        if(StringUtils.isBlank(accessToken)) {
            String accessTokenUrl = String.format(CommonContent.GET_ACCESS_TOKEN_URL, CommonContent.APP_ID, CommonContent.APP_SECRET);
            String accessTokenJsonStr = HttpUtil.get(accessTokenUrl);
            JSONObject accessTokenJson = JSONObject.parseObject(accessTokenJsonStr);
            if (null != accessTokenJson && !accessTokenJson.isEmpty()) {
                accessToken = accessTokenJson.getString("access_token");
                if (StringUtils.isNotBlank(accessToken)) {
                    map.put("access_token", accessToken);
                    map.expire(6600, TimeUnit.SECONDS);
                    log.info("时间：{}， 成功获取accessToken；值为：{}", DateUtil.formatDate(new Date()), accessToken);
                } else {
                    String errCode = accessTokenJson.getString("errcode");
                    String errMsg = accessTokenJson.getString("errmsg");
                    log.info("时间：{}， 获取accessToken失败；errCode：{}，errMsg：{}", DateUtil.formatDate(new Date()), errCode, errMsg);
                }
            }
        }
        return accessToken;
    }

    @Override
    public String getUserInfo(String openId) {
        String url = String.format(CommonContent.GET_USERINFO_URL,getAccessToken(),openId);
        String result = HttpUtil.get(url);
        return result;
    }
}
