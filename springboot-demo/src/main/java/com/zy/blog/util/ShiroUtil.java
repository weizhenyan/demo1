package com.zy.blog.util;

import com.zy.blog.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil {
    public static AccountProfile getPofile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
