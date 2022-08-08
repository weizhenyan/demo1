package com.zy.blog.config;

import com.zy.blog.shiro.AccountRealm;
import com.zy.blog.shiro.JwtFilter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;
@Configuration
public class ShiroConfig {

    @Value("${shiro-redis.redis-manager.host}")
    private String host;

    @Value("${shiro-redis.redis-manager.port}")
    private Integer port;
    @Value("${shiro-redis.redis-manager.passord}")
    private  String password;
    @Value("${shiro-redis.redis-manager.database}")
    private int database;

    @Autowired
    JwtFilter jwtFilter;
    @Bean
    public SessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();

        sessionManager.setSessionDAO(redisSessionDAO);
        return  sessionManager;
    }
    @Bean
    public SessionsSecurityManager securityManager(AccountRealm accountRealm,
                                                   SessionManager sessionManager,
                                                   RedisCacheManager redisCacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(accountRealm);

        securityManager.setSessionManager(sessionManager);
        securityManager.setCacheManager(redisCacheManager);
        return securityManager;

    }
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        Map filterMap = new LinkedHashMap();
        filterMap.put("/**","jwt");
        chainDefinition.addPathDefinitions(filterMap);
        return chainDefinition;
    }

    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager, ShiroFilterChainDefinition shiroFilterChainDefinition) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //对于没有登录的请求码拦截后返回JSON的状态信息码，
        Map filterMap = new LinkedHashMap<>();
        filterMap.put("jwt", jwtFilter);
        shiroFilterFactoryBean.setFilters(filterMap);

        Map filterChainDefinitionMap = shiroFilterChainDefinition.getFilterChainMap();
        //配置不登录就可以访问的资源  anon
        filterChainDefinitionMap.put("/login","anon");
        filterChainDefinitionMap.put("/","anon");
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/js/**","anon");

        //logout是shiro提供的过滤器
        filterChainDefinitionMap.put("/logout","logout");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    /**
     * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
     *
     * @return
     */
    @Bean
    public AccountRealm myAccountRealm() {
        AccountRealm myShiroRealm = new AccountRealm();
        return myShiroRealm;
    }

    /**
     * 配置shiro redisManager
     *
     * @return
     */
    @Bean
    public RedisManager redisManager() {
        System.out.println(host+":"+port+"--"+password);
        RedisManager redisManager = new RedisManager();
        //设置redis的地址，需要带端口号
        redisManager.setHost(host+":"+port);
        redisManager.setTimeout(2000);
        //服务端没有设置密码，这里就不需要了
        redisManager.setPassword(password);
        redisManager.setDatabase(0);
        return redisManager;
    }

    /**
     * cacheManager 缓存 redis实现
     *
     * @return
     */
    @Bean("redisCacheManager")
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     */
    @Bean("redisSessionDAO")
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }
    /**
     * @Description:此配置类注入bean和使用@Value注解需要将此方法改为static
     * @Author: ysw
     * @param
     * @Return org.apache.shiro.spring.LifecycleBeanPostProcessor
     * @Date: 2020/03/24 10:12
     */
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
