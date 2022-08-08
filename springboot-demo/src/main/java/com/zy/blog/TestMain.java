package com.zy.blog;

import com.zy.blog.model.User;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @Classname TestMain
 * @Description TODO
 * @Date 2021/11/12 23:59
 * @Created by 飞燕
 */
public class TestMain {
    public static void main(String[] args) {
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
                        .filter(tar-> Objects.equals(tar.getUsername(),sur.getUsername()))
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
