package com.example.forumsystem.controller;

import com.example.forumsystem.pojo.DiscussPost;
import com.example.forumsystem.pojo.User;
import com.example.forumsystem.service.DiscussPostService;
import com.example.forumsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sparkle6979l
 * @version 1.0
 * @data 2023/4/20 15:06
 */
@Controller
public class HelloController {
    @Autowired
    private DiscussPostService discussPostService;
    @Autowired
    private UserService userService;

    @GetMapping("/index")

    public String getIndexPage(Model model){
        //查询10条数据
        List<DiscussPost> list=discussPostService.findDiscussPosts(0,0,10);
        //用Map把post和user装到一起
        List<Map<String,Object>> discussPosts=new ArrayList<>();
        //遍历post，用post里的id查询user
        if(list!=null){
            for (DiscussPost post:list){
                Map<String,Object> map=new HashMap<>();
                map.put("post",post);
                User user=userService.findUserById(post.getUserId());
                map.put("user",user);
                discussPosts.add(map);
            }
        }
        System.out.println(discussPosts);
        //把我们准备好的数据传给model
        model.addAttribute("discussPosts",discussPosts);

        // 返回模板
        return "/index";
    }

}
