package com.example.demo;

import com.zy.blog.SpringbootDemoApplication;
import com.zy.blog.model.User;
import com.zy.blog.service.UserService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootDemoApplication.class)
class DemoApplicationTests {
    @Autowired
    UserService userService;

    @Test
    void contextLoads() {

//        User user = userService.getUserById(12l);
        User user = userService.getUserByName("xiaop");
        System.out.println(user.getSex());
    }

    @Test
    void test() {
        List<User> resultList = Lists.newArrayList();
        List<User> sourceList = Lists.newArrayList();
        List<User> targetList = Lists.newArrayList();
        User u = new User();
        u.setUsername("zs");
        User u1 = new User();
        u1.setUsername("zs1");
        User u2 = new User();
        u2.setUsername("zs2");
        User u3 = new User();
        sourceList.add(u1);
        sourceList.add(u);
        sourceList.add(u2);
        u3.setUsername("zs");
        User u4 = new User();
        u4.setUsername("zs4");
        targetList.add(u3);
        targetList.add(u4);

        resultList = sourceList.stream()
                .map(sur->targetList.stream()
                .filter(tar->Objects.equals(tar.getUsername(),sur.getUsername()))
                .findFirst()
                .map(tar->{
                    sur.setAvatar("ddddddddddddddd");
                    return sur;
                }).orElse(null))
                .collect(Collectors.toList());
        System.out.println(resultList);
        System.out.println();
    }
}
