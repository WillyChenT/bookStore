package dev.ming.bookStore.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ming.bookStore.utils.JWTUtils;
import dev.ming.bookStore.utils.JsonData;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try{
            String accessToken = request.getHeader("token");
            if(StringUtils.isBlank(accessToken)){
                accessToken = request.getParameter("token");
            }

            if(StringUtils.isNotBlank(accessToken)){
                Claims claims = JWTUtils.checkJWT(accessToken); //解析token合法性
                if(null == claims){
                    sendJsonMessage(response, JsonData.buildError("登入過期，請重新登入"));
                    return false;
                }
                //從token解析完後的claims中取出資料
                Integer id = (Integer) claims.get("id");
                String name = (String) claims.get("name");

                request.setAttribute("user_id",id);
                request.setAttribute("name", name);

                return true;
            }
        }catch(Exception e){}
        sendJsonMessage(response, JsonData.buildError("登入過期，請重新登入"));
        return false;
    }

    /**
     * 回傳Json數據給前端
     * @param response
     * @param obj
     */
    public static void sendJsonMessage(HttpServletResponse response, Object obj){

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json; charset=utf-8"); //宣告JSON型態
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(obj)); //寫入轉換Json
            writer.close();
            response.flushBuffer();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
