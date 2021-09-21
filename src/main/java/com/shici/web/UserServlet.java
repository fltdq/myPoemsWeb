package com.shici.web;

import com.google.gson.Gson;
import com.shici.pojo.User;
import com.shici.service.UserService;
import com.shici.service.impl.UserServiceImpl;
import com.shici.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * FileName: UserServlet
 * Description:
 * Author: CSH
 * Date: 2020/12/22 20:52
 * Version: 1.0
 */
public class UserServlet extends BaseServlet{
   private UserService userService = new UserServiceImpl();

    public void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数
        String username = req.getParameter("username");
        //调用userService.existUsername();
        boolean existsUsername = userService.existsUsername(username);
        //把返回的结果封装成map对象
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);
    }

    public void loginout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /**
         * @Description:
         * 1、销毁Session中用户登录的信息（或者销毁Session） 2、重定向到首页（或登录页面）
         * @Author: CSH
         * @Date: 2021/1/2 19:20
         * @param req
         * @param resp
         * @Return:void
         * @Version: 1.0.0
         */
        //1、销毁Session中用户登录的信息（或者销毁Session）
        req.getSession().invalidate();
        //2、重定向到首页（或登录页面）
        resp.sendRedirect(req.getContextPath());
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //调用userService.login()处理登录业务
        User loginUser = userService.login(new User(null, username, password, null));
        //如果等于null，说明登陆失败
        if(loginUser == null){
            //把错误信息，和回显的表单项信息，保存到Request域中
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",username);
            //跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            //登录成功
            //保存用户登录信息到Session域中
            req.getSession().setAttribute("user",loginUser);
            //跳到登录成功页面login_success.html
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    public void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        //2、检查验证码 == 写死，要求验证码为：abcde
        if (token != null && token.equalsIgnoreCase(code)){
            //3、检查 用户名是否可用
            if (userService.existsUsername(username)){
                System.out.println("用户名[" + username +"]已存在！");

                //把回显信息，保存到Request域中
                req.setAttribute("msg","用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                //跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {
                //可用
                //调用Service保存到数据库
                userService.registUser(new User(null,username,password,email));
                //userService.registUser(user);

                //跳转注册成功页面
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else{
            //把回显信息，保存到Request域中
            req.setAttribute("msg","验证码错误！！");
            req.setAttribute("username",username);
            req.setAttribute("email",email);

            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }
}
