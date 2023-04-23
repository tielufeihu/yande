package game605.Interceptors;

import game605.Application;
import game605.servicelmpl.AuthorityService;
import game605.servicelmpl.TokenService;
import game605.servicelmpl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Configuration
public class RoleInterceptor implements HandlerInterceptor {

    @Autowired
    TokenService ts;

    @Autowired
    AuthorityService as;

    @Autowired
    UserService us;

    private static Logger log = LoggerFactory.getLogger(Application.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        if (request.getMethod().equals("OPTIONS")){
            //response.setStatus(response.SC_OK);
            return true;
        }

        log.info("request请求地址path[{}] uri[{}]", request.getServletPath(),request.getRequestURI());
        String urlStr = request.getRequestURI();

        if(urlStr.equals("/role/list")  || urlStr.equals("/role/search") || urlStr.equals("/role/getInfo"))
            return true;

        String token = request.getHeader("User-Token");
        System.out.println("token:" + token);
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
        System.out.println("用户id：" + userId);
        if(userId == -1){
            log.info(" token无效"+ urlStr);
            response.sendError(412,"token无效！");
        }
        // 权限检测
        if(!checkAuth(userId, urlStr))
        {
            log.info(" 权限不足或请求格式错误"+ urlStr);
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
            case "/role/add":
                authId = 8;
                break;
            case "/role/del":
                authId = 9;
                break;
            case "/role/update":
                authId = 11;
                break;
            case "/role/list":
            case "/role/search":
                return true;
        }
        System.out.println("authId:" + authId);
        if(authId == -1)
        {
            log.info("请求不存在！");
            return false;
        }
        return us.userIfAuth(userid, authId);
    }

}
