package com.example.forumsystem.controller;

import com.example.forumsystem.pojo.User;
import com.example.forumsystem.service.UserService;
import com.example.forumsystem.utils.ForumConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * @author sparkle6979l
 * @version 1.0
 * @data 2023/4/20 20:42
 */
@Controller
public class LoginController implements ForumConstant {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String getRegisterPage(){
        return "/site/register";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "/site/login";
    }


    @PostMapping("/register")
    public String register(User user, Model model){
        Map<String, Object> map = userService.register(user);

        // 如果注册成功
        if (map == null || map.isEmpty()) {
            model.addAttribute("msg", "注册成功,我们已经向您的邮箱发送了一封激活邮件,请尽快激活!");
            model.addAttribute("target", "/index");
            return "/site/operate-result";
        } else {
            model.addAttribute("usernameMsg", map.get("usernameMsg"));
            model.addAttribute("passwordMsg", map.get("passwordMsg"));
            model.addAttribute("emailMsg", map.get("emailMsg"));
            return "/site/register";
        }
    }

    // http://localhost:8080/activation/101/code
    @GetMapping("/activation/{userID}/{code}")
    public String activate(@PathVariable int userID, @PathVariable String code, Model model){
        int activate = userService.activate(userID, code);
        if(activate == ACTIVATION_SUCCESS) {
            model.addAttribute("msg", "激活成功,您的账号已经可以正常使用了!");
            model.addAttribute("target", "/login");
        }else if(activate == ACTIVATION_REAPEAT) {
            model.addAttribute("msg", "激活成功,您的账号已经可以正常使用了!");
            model.addAttribute("target", "/index");
        }else{
            model.addAttribute("msg", "激活失败，激活码不匹配");
            model.addAttribute("target", "/index");
        }

        return "/site/operate-result";

    }
}
