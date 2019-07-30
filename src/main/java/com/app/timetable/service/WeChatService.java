package com.app.timetable.service;

import org.springframework.stereotype.Service;

/**
 * @author Judith
 * @date 2019/7/30 15:06
 * @description
 */
public interface WeChatService {

    String getAccessToken();

    String getUserInfo(String openId);
}
