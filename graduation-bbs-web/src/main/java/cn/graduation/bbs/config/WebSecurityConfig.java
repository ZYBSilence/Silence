package cn.graduation.bbs.config;

import cn.graduation.bbs.common.WebResponse;
import cn.graduation.bbs.enums.StatusCodeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @desc: 使用Spring Security完成身份验证和权限管理
 * @author: zyb
 * @since: 2019/12/31 10:11
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DbUserDetailServiceImpl dbUserDetailServiceImpl;

    /**
     * 匹配 "/" 路径，不需要权限即可访问
     * 匹配 "/user" 及其以下所有路径，都需要 "USER" 权限
     * 登录地址为 "/login"，登录成功默认跳转到页面 "/user"
     * 退出登录的地址为 "/logout"，退出成功后跳转到页面 "/login"
     * 禁用 CSRF（便于调试）
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/api/user/save").permitAll()
                //表示访问"/api/admin/**"路径需要"ADMIN"角色
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("/api/user/**","/api/bbs/user/**").hasRole("USER")
                .and()
                //定义登录界面，未登录时，会自动跳转到该页面
                .formLogin()
                .loginPage("/api/bbs/login")
                //和表单登录相关的接口统统直接通过
                .permitAll()
                //登陆失败处理
                .and()
                .logout()
                .logoutUrl("/api/bbs/logout")
                .logoutSuccessUrl("/api/bbs/login")
                .permitAll()
                .and()
                //解决security中的X-Frame-Options的默认DENY（frame不能使用的问题）
                .headers().frameOptions().disable()
                .and()
                .csrf().disable();
//        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 这部分时使用json数据完成登录验证的配置
     *
     * @return
     * @throws Exception
     */
    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        //登录成功处理器(可以在这里配置登录成功的回调)
        filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest rep, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                WebResponse web = new WebResponse();
                web.setMessage("登录成功");
                out.write(new ObjectMapper().writeValueAsString(web));
                out.flush();
                out.close();
            }
        });
        //登录失败处理器(可以在这里配置登录失败的回调)
        filter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest rep, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                WebResponse web = new WebResponse();
                web.setCode(StatusCodeEnum.LOGIN_ERROR.getCode());
                web.setMessage(StatusCodeEnum.LOGIN_ERROR.getMessage());
                out.write(new ObjectMapper().writeValueAsString(web));
                out.flush();
                out.close();
            }
        });
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    /**
     * 添加UserDetailService,实现自定义登录校验
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(dbUserDetailServiceImpl).passwordEncoder(new BCryptPasswordEncoder());

        //模拟两个用户
//        auth.inMemoryAuthentication()
//                .withUser("zhang").roles("ADMIN").password("$2a$10$doA9AomVXcd2k4uPjisy0OvNL4T21qefLCuYQkv1rsGbynvl45AiO")
//                .and()
//                .withUser("wang").roles("USER").password("$2a$10$doA9AomVXcd2k4uPjisy0OvNL4T21qefLCuYQkv1rsGbynvl45AiO");
    }

    /**
     * 密码加密
     *
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 验证密码加密
     *
     * @param args
     */
    public static void main(String[] args) {
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        //encode()加密，matches()密码匹配
        String encode = b.encode("123456");
        boolean m = b.matches("123456", "$2a$10$doA9AomVXcd2k4uPjisy0OvNL4T21qefLCuYQkv1rsGbynvl45AiO");
        System.out.println(encode);
        System.out.println(m);
        System.out.println(b.encode("123456"));
    }

    /**
     * 设置拦截忽略文件夹，可以对静态资源放行
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/admin/**", "/json/**", "/res/**");
    }

    /**
     * 让ROLE_ADMIN用户继承ROLE_USER用户的所有权限
     *
     * @return
     */
    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_ADMIN > ROLE_USER";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }
}
