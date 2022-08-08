package com.zy.blog.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.zy.blog.model.User;
import com.zy.blog.service.UserService;
import com.zy.blog.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AccountRealm extends AuthorizingRealm {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
                //super.supports(token);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute("userInfo");
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 用户的角色集合
        Set<String> roles = new HashSet<>();
        //1为管理员
        //Integer)user.getIsAdmin()==1?"admin":"user")
        roles.add(user.getIsAdmin().getType()==1?"admin":"user");
        info.setRoles(roles);
        return info;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;

        String userId = jwtUtils.getClaimsByToken((String) jwtToken.getPrincipal()).getSubject();

        User user = userService.getUserById(Long.parseLong(userId));

        if(user==null) {
            throw new UnknownAccountException("账户不存在");
        }
        if(user.getStatus()==-1) {
            throw new LockedAccountException("账户已被锁定");
        }
        System.out.println("--------------------");
        AccountProfile profile = new AccountProfile();
        BeanUtil.copyProperties(user,profile);
        return new SimpleAuthenticationInfo(profile,jwtToken.getCredentials(),getName());
    }

    /**
     * 重写方法，清除当前用户的 授权缓存
     * @param principals
     */
    @Override
    protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }
}
