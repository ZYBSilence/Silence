package cn.graduation.bbs.config;

import cn.graduation.bbs.dao.UserDao;
import cn.graduation.bbs.entity.UserEntity;
import cn.graduation.bbs.utils.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc: 自定义UserDetailsService, 将用户信息和权限注入进来
 * @author: zyb
 * @since: 2020/3/23 16:53
 */
@Service
public class DbUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

//    使用的默认UserDetails，里面属性比较少
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity userEntity = userDao.findByUsername(username);
//        if (userEntity == null) {
//            throw new UsernameNotFoundException("用户不存在");
//        }
//        List<SimpleGrantedAuthority> simpleGrantedAuthority = new ArrayList<>();
//        simpleGrantedAuthority.add(new SimpleGrantedAuthority(userEntity.getRoleName()));
//        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPassword(), simpleGrantedAuthority);
//    }

    /**
     * 使用自定义UserDetails
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userDao.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("用户不存在");
        } else if (userEntity.getDelFlag() == 1) {
            throw new DisabledException("用户被封禁");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userEntity.getRoleName()));
        return new MyUserDetails(userEntity.getId(), authorities, userEntity.getUsername(), userEntity.getPassword(), userEntity.getNickName(), userEntity.getPhoto());
    }
}
