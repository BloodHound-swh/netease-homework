package com.netease.homework.controller.useradmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netease.homework.entity.User;
import com.netease.homework.service.UserService;
import com.netease.homework.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/useradmin")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/listuser", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listUser() {
        Map<String, Object> modelMap = new HashMap<>();
        List<User> list;
        try {
            list = userService.getUserList();
            modelMap.put("userList", list);
            modelMap.put("totalNum", list.size());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            return modelMap;
        }
        return modelMap;
    }

    @RequestMapping(value = "/getuser", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getUser(@RequestParam Integer userId) {
        Map<String, Object> modelMap = new HashMap<>();
        User userCondition = new User();
        userCondition.setUserId(userId);
        try {
            User user = userService.getUserByCondition(userCondition);
            modelMap.put("success", true);
            modelMap.put("user", user);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.toString());
            e.printStackTrace();
            return modelMap;
        }
        return modelMap;
    }

    @RequestMapping(value = "/buyerhome", method = RequestMethod.GET)
    private String buyerHome() {
        return "/buyer/buyerhome";
    }

    @RequestMapping(value = "/sellerhome", method = RequestMethod.GET)
    private String sellerHome() {
        return "/seller/sellerhome";
    }

    @RequestMapping(value = "/loginpage", method = RequestMethod.GET)
    private String login() {
        return "/login/login";
    }

    @RequestMapping(value = "/userlogin", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> userLogin(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        String userStr = HttpServletRequestUtil.getString(request, "userStr");
        ObjectMapper mapper = new ObjectMapper();
        User userCondition = null;
        try {
            userCondition = mapper.readValue(userStr, User.class);
            User user = userService.getUserByCondition(userCondition);
            if (user == null) {
                modelMap.put("success", false);
                modelMap.put("errMsg", "账号或者密码不正确");
                return modelMap;
            }
            request.getSession().setAttribute("currentUser", user);
            modelMap.put("success", true);
            modelMap.put("userId", user.getUserId());
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        return modelMap;
    }

}
