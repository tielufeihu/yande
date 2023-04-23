package game605.Interceptors;

import game605.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class TestAllInterceptor implements HandlerInterceptor {

    private static Logger log = LoggerFactory.getLogger(Application.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("request请求地址path[{}] uri[{}]", request.getServletPath(),request.getRequestURI());
        //request.getHeader(String) 从请求头中获取数据
        // 从请求头中获取用户token（登陆凭证根据业务而定）
        String token = request.getHeader("User-Token");
        System.out.println("获取到token：" + token);
        if(token == null){
            return false;
        }
        int userId = getUserId(token);
        if (userId != 0 && checkAuth(userId,request.getRequestURI()))
        {
            return true;
        }
        //这里的异常是我自定义的异常，系统抛出异常后框架捕获异常然后转为统一的格式返回给前端， 其实这里也可以返回false
        //throw new FastRuntimeException(20001,"No access");
        return false;
    }

    private int getUserId(String token){
        System.out.println("getUserId: 形参：" + token);
        if(token.equals("1000"))
            return 100;
        return 1;
    }

    private boolean checkAuth(int userid, String url){
        if(userid == 1)
            return true;
        else
            return false;
    }

}
