package com.app.timetable.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Judith
 * @date 2019/4/29 14:56
 * @description 验证token拦截器
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String token = request.getHeader("token");
//
//        // 如果不是映射到方法直接通过
//        if(!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//
//        //检查是否有passtoken注释，有则跳过认证
//        if(method.isAnnotationPresent(PassToken.class)) {
//            PassToken passToken = method.getAnnotation(PassToken.class);
//            if(passToken.required()) {
//                return true;
//            }
//        }
//
//        if(method.isAnnotationPresent(UserLoginToken.class)) {
//            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
//            if(userLoginToken.required()) {
//                //执行认证
//                if(token == null) {
//                    throw new RuntimeException("无token，请重新登录");
//                }
//
//                // 获取 token 中的 user id
//                String userId;
//                try {
//                    userId = JWT.decode(token).getAudience().get(0);
//                } catch (Exception e) {
//                    throw new RuntimeException("401 - 未授权: 由于凭据无效，访问被拒绝");
//                }
//                String value = jedis.get(userId);
//                jedis.close();
//                if(!StringUtils.isNotBlank(value)) {
//                    throw new RuntimeException("无token，请重新登录");
//                }
//
//                JSONObject userInfoJson = JSONObject.parseObject(value);
//
//                //验证token
//                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userInfoJson.getString("openId"))).build();
//                try {
//                    jwtVerifier.verify(token);
//                } catch (JWTVerificationException e) {
//                    throw new RuntimeException("401 - 未授权: 由于凭据无效，访问被拒绝");
//                }
//                return true;
//            }
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
