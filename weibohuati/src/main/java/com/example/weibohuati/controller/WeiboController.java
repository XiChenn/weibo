package com.example.weibohuati.controller;

import com.example.weibohuati.model.User;
import com.example.weibohuati.model.Weibo;
import com.example.weibohuati.service.result.ServiceResult;
import com.example.weibohuati.weiboservice.facade.WeiboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/weibo")
public class WeiboController extends BaseController {

    @Autowired
    private WeiboService weiboService;

    @GetMapping("/get.html")
    public ModelAndView get(@RequestParam(name="id") Integer id) {
        ServiceResult<Weibo> weiboServiceResult = weiboService.get(id);

        ModelAndView modelAndView;
        if (weiboServiceResult == null) {
            modelAndView = new ModelAndView("error");
            modelAndView.addObject("content", "Get Weibo service exception");
        } else if (!weiboServiceResult.isSuccess()) {
            modelAndView = new ModelAndView("error");
            modelAndView.addObject("content", weiboServiceResult.getErrorMessage());
        } else {
            modelAndView = new ModelAndView("get");
            modelAndView.addObject("data", weiboServiceResult.getData());
        }
        return modelAndView;
    }

    @PostMapping("/publish")
    public Map publish(@RequestParam(name="content") String content, @RequestParam(name="imgUrl") String imgUrl) {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return getErrorResponse("User not login. Cannot publish Weibo");
        }

        Weibo weibo = new Weibo();
        weibo.setContent(content);
        weibo.setImg_url(imgUrl);
        weibo.setCreate_time(new Date());
        weibo.setUser_id(currentUser.getId());
        ServiceResult<Weibo> serviceResult = weiboService.create(weibo);
        if (serviceResult == null) {
            return getErrorResponse("Weiboservice exception");
        } else if (!serviceResult.isSuccess()) {
            return getErrorResponse(serviceResult.getErrorMessage());
        }
        return getResponse(serviceResult.getData());
    }

    @GetMapping("/publish.html")
    public ModelAndView publishhtml() {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            ModelAndView modelAndView = new ModelAndView("redirect:/user/login.html");
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("publish");
        return modelAndView;
    }

    @GetMapping("/list")
    public Map list() {
        ServiceResult<List<Weibo>> list = weiboService.list();
        if (list == null) {
            return getErrorResponse("Calling Weiboservice Exception.");
        }
        if (list.isSuccess()) {
            return getResponse(list.getData());
        }
        return getErrorResponse(list.getErrorMessage());
    }

    @PostMapping("/like")
    public Map like(@RequestParam(name="weiboId") Integer weiboId) {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return getErrorResponse("User not login. Cannot like Weibo");
        }


        ServiceResult serviceResult = weiboService.like(currentUser.getId(), weiboId);
        if (serviceResult == null) {
            return getErrorResponse("Weiboservice exception");
        } else if (!serviceResult.isSuccess()) {
            return getErrorResponse(serviceResult.getErrorMessage());
        }
        return getResponse(serviceResult.getData());
    }
}
