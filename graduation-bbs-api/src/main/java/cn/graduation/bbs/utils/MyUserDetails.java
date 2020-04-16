package cn.graduation.bbs.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @desc: 自定义SpringSecurity框架中的UserDetails
 * @author: zyb
 * @since: 2020/3/31 15:11
 */
public class MyUserDetails implements UserDetails {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 角色
     */
    private Collection<GrantedAuthority> authorities;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String photo;

    public MyUserDetails() {
    }

    public MyUserDetails(Integer id, Collection<GrantedAuthority> authorities, String username, String password, String nickname, String photo) {
        this.id = id;
        this.authorities = authorities;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
