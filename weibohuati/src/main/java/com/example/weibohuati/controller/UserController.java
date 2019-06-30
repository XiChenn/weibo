package com.example.weibohuati.controller;

import com.example.weibohuati.model.User;
import com.example.weibohuati.service.UserService;
import com.example.weibohuati.service.result.ServiceResult;
import com.example.weibohuati.weiboservice.facade.TestWeiboService;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private TestWeiboService testWeiboService;

    @GetMapping("/testweibo")
    public Map testWeibo() {
        testWeiboService.test();
        return getResponse(null);
    }

    @GetMapping("/{id}")
    public Map getUser(@PathVariable(name="id") Integer id) {
        User user = userService.getUser(id);
        return getResponse(user);
    }

    @GetMapping("/register.html")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("register");
        return modelAndView;
    }

    @GetMapping("/login.html")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @PostMapping("/register")
    public Map register(@RequestParam(name="loginName") String loginName,
                        @RequestParam(name="password") String password,
                        @RequestParam(name="nickName") String nickName,
                        @RequestParam(name="gender") Integer gender,
                        @RequestParam(name="age") Integer age,
                        @RequestParam(name="avatarUrl") String avatarUrl) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtils.isNullOrEmpty(loginName) || StringUtils.isNullOrEmpty(password) ||
                StringUtils.isNullOrEmpty(nickName) || StringUtils.isNullOrEmpty(avatarUrl)) {
            return getErrorResponse("Invalid Input");
        }

        if (gender.intValue() != 1 && gender.intValue() != 2) {
            return getErrorResponse("Invalid Input for gender");
        }

        ServiceResult serviceResult = userService.register(loginName, password, nickName, age, gender, avatarUrl);
        if (serviceResult.isSuccess()) {
            return getResponse(null);
        }

        return getErrorResponse((String) serviceResult.getData());
    }

    @PostMapping("/login")
    public Map login(@RequestParam(name="loginName") String loginName,
                     @RequestParam(name="password") String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        ServiceResult serviceResult = userService.login(loginName, password);
        if (!serviceResult.isSuccess()) {
            return getErrorResponse((String) serviceResult.getData());
        }
        httpServletRequest.getSession().setAttribute("loginUser", serviceResult.getData());
        return getResponse(serviceResult.getData());
    }
}
