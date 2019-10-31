package com.demo.test.controller;

import com.demo.common.domain.AjaxMessage;
import com.demo.test.domain.User;
import com.demo.test.service.UserService;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping("register")
    @ResponseBody
    public AjaxMessage register(User condition) {
        try {
            if (StringUtil.isBlank(condition.getUsername())) {
                return AjaxMessage.error("username is null");
            }
            if (StringUtil.isBlank(condition.getEmail())) {
                return AjaxMessage.error("email is null");
            }
            if (StringUtil.isBlank(condition.getPassword())) {
                return AjaxMessage.error("password is null");
            }
            userService.save(condition);
            return AjaxMessage.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxMessage.error(e.getMessage());
        }
    }

    @PostMapping("checkUsername")
    @ResponseBody
    public AjaxMessage checkUsername(String username) {
        try {
            User tmp = userService.findByUsername(username);
            if (tmp != null) {
                return AjaxMessage.error("username exist");
            }
            return AjaxMessage.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxMessage.error(e.getMessage());
        }
    }

    @PostMapping("login")
    @ResponseBody
    public AjaxMessage login(User condition) {
        try {
            if (StringUtil.isBlank(condition.getUsername())) {
                return AjaxMessage.error("username is null");
            }
            if (StringUtil.isBlank(condition.getPassword())) {
                return AjaxMessage.error("password is null");
            }
            boolean b = userService.checkPassword(condition);
            if (!b) {
                return AjaxMessage.error("username or password error");
            }
            return AjaxMessage.success();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxMessage.error(e.getMessage());
        }
    }
}
