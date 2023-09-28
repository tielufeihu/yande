package game605.Interceptors;

import game605.Application;
import game605.servicelmpl.AuthorityService;
import game605.servicelmpl.TokenService;
import game605.servicelmpl.UserService;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;


@Configuration
public class ImgInterceptor implements HandlerInterceptor {

    @Autowired
    TokenService ts;

    @Autowired
    AuthorityService as;

    @Autowired
    UserService us;

    private static Logger log = LoggerFactory.getLogger(Application.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        //response.setHeader("Access-Control-Allow-origin","http://192.168.123.187:8090/");
        response.setHeader("Access-Control-Allow-origin","*");
        response.setHeader ("Access-Control-Allow-Methods","POST，GET，OPTIONS，DELETE，PUT，HEAD");
        response.setHeader ("Access-Control-Allow-Headers", "content-type,Token,User-Token,Content-Type");
        response.setHeader( "Access-control-Max-Age","3600");

        if (request.getMethod().equals("OPTIONS")){
            //response.setStatus(response.SC_OK);
            return true;
        }

        log.info("request请求地址path[{}] uri[{}]", request.getServletPath(),request.getRequestURI());
        String urlStr = request.getRequestURI();

        String token = request.getHeader("User-Token");
        if(token == null){
            log.info("token 请先登录"+ urlStr);
            response.sendError(412,"请先登录！");
            return false;
        }
        // token 转成 long
        long l_token;
        try{
            l_token = Long.parseLong(token);
        }catch (Exception e){
            log.info(" token格式错误"+ urlStr);
            response.sendError(412,"token格式错误！");
            return false;
        }
        // 获取该用户id
        int userId = getUserId(l_token);
        log.info("用户Token: {}", token);
        if(userId == -1){
            log.info("token无效, 访问url:[{}]",urlStr);
            response.sendError(412,"token无效！");
            return false;
        }
        log.info("用户id: {}", userId);
        // 权限检测
        if(!checkAuth(userId, urlStr))
        {
            log.info("权限不足或请求格式错误,访问 url[{}]", urlStr);
            response.sendError(412,"权限不足或请求格式错误！");
            return false;
        }
        else{
            log.info("token 有效执行操作"+ urlStr);
            return true;
        }
    }

    private int getUserId(Long token){
        return ts.checkTokenAndGetUserId(token);
    }

    private boolean checkAuth(int userid, String url){
        int authId = -1;
        switch (url)
        {
            case "/yandeApi/img/delImgInfo":
                authId = 5;
                break;
            case "/yandeApi/img/addImg":
                authId = 6;
                break;
            case "/yandeApi/img/addTag":
                authId = 4;
                break;
        }
        System.out.println("authId:" + authId);
        if(authId == -1)
            return false;
        return us.userIfAuth(userid, authId);
    }

}
