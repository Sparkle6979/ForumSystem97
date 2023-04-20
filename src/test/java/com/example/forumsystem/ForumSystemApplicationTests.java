package com.example.forumsystem;

import com.example.forumsystem.dao.DiscussPostMapper;
import com.example.forumsystem.dao.UserMapper;
import com.example.forumsystem.pojo.DiscussPost;
import com.example.forumsystem.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ForumSystemApplicationTests {

    @Autowired
    DiscussPostMapper discussPostMapper;
    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void DiscussPostTest(){
        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(0, 0, 10);
        for (DiscussPost discussPost : discussPosts) {
            System.out.println(discussPost);
        }
    }

    @Test
    void selectDiscussPostRowsTest(){
        int discussPosts = discussPostMapper.selectDiscussPostRows(0);
        System.out.println(discussPosts);
//        for (DiscussPost discussPost : discussPosts) {
//            System.out.println(discussPost);
//        }
    }

    @Test
    void UserID(){
        User user = userMapper.selectById(11);
        System.out.println(user);
//        for (DiscussPost discussPost : discussPosts) {
//            System.out.println(discussPost);
//        }
    }

}
