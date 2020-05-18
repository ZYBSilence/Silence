package cn.graduation.bbs.service.impl;

import cn.graduation.bbs.common.*;
import cn.graduation.bbs.dao.RoleDao;
import cn.graduation.bbs.dao.UserDao;
import cn.graduation.bbs.dto.user.UserDTO;
import cn.graduation.bbs.entity.UserEntity;
import cn.graduation.bbs.enums.StatusCodeEnum;
import cn.graduation.bbs.service.UserService;
import cn.graduation.bbs.utils.EmptyUtils;
import cn.graduation.bbs.utils.MailUtils;
import cn.graduation.bbs.utils.OperUserUtils;
import cn.graduation.bbs.vo.user.UserDelFilter;
import cn.graduation.bbs.vo.user.UserFilter;
import cn.graduation.bbs.vo.user.UserModifyPwdFilter;
import cn.graduation.bbs.vo.user.UserVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @desc: 用户管理的业务层实现类
 * @author: zyb
 * @since: 2019/12/31 17:42
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;


    /**
     * 根据条件分页查询用户信息
     *
     * @param userFilter
     * @return
     */
    @Override
    public WebResponse queryUserList(UserFilter userFilter) {
        if (EmptyUtils.isEmpty(userFilter)) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        PageHelper.startPage(userFilter.getPageNum(), userFilter.getPageSize());
        List<UserEntity> userEntities = userDao.queryUserList(genUserFilter(userFilter));
        //查询数据为空
        if (userEntities == null || userEntities.size() == 0) {
            web.setCode(StatusCodeEnum.DATA_IS_NULL.getCode());
            web.setMessage("未查询相关的用户");
            return web;
        }
        ListPage<UserEntity> userListPage = new ListPage<>(userEntities);
        List<UserVO> list = new ArrayList();
        userEntities.forEach(v -> list.add(genUserEntity(v)));
        web.setData(new ListPage<>(list, userListPage.getPage()));
        return web;
    }

    /**
     * 新增用户/注册用户
     *
     * @param userEntity
     * @return
     */
    @Override
    public WebResponse save(UserEntity userEntity) {
        if (EmptyUtils.isEmpty(userEntity) || EmptyUtils.isEmpty(userEntity.getUsername()) || EmptyUtils.isEmpty(userEntity.getPassword())
                || EmptyUtils.isEmpty(userEntity.getNickName()) || EmptyUtils.isEmpty(userEntity.getGender()) || EmptyUtils.isEmpty(userEntity.getEmail())) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        //判断用户是否已经存在
        if (exist(userEntity.getUsername())) {
            web.setCode(StatusCodeEnum.ERROR.getCode());
            web.setMessage("用户名已存在");
            return web;
        }
        //发送邮件邮件通知在Silence社区注册新用户
        String content = "欢迎加入Silence社区<a href='http://115.29.179.11:8080'><button>点此进入</button></a>";
        boolean sendMail = MailUtils.sendMail(userEntity.getEmail(), content, "Silence社区");
        if (!sendMail) {
            web.setCode(StatusCodeEnum.ERROR.getCode());
            web.setMessage("请输入正确的邮箱");
            return web;
        }
        //设置默认前端显示头像
        String upPhoto = "/admin/images/upAvatar.jpg";
        if (EmptyUtils.isEmpty(userEntity.getPhoto()) || upPhoto.equals(userEntity.getPhoto())) {
            userEntity.setPhoto("/admin/images/moren.jpg");
        }
        Integer roleId;
        userEntity.setCreateTime(new Date());
        BCryptPasswordEncoder bcEncoder = new BCryptPasswordEncoder();
        userEntity.setPassword(bcEncoder.encode(userEntity.getPassword()));
        userDao.save(userEntity);
        //获取新增用户的id
        final Integer userId = userEntity.getId();
        if (EmptyUtils.isEmpty(userEntity.getRoleId())) {
            //如果为空则默认为普通用户
            roleId = 1;
        } else {
            roleId = userEntity.getRoleId();
        }
        roleDao.saveUr(userId, roleId);
        return web;
    }

    /**
     * 更新用户信息
     *
     * @param userEntity
     * @return
     */
    @Override
    public WebResponse update(UserEntity userEntity) {
        //用户名不能修改
        if (EmptyUtils.isEmpty(userEntity) || EmptyUtils.isEmpty(userEntity.getId()) || EmptyUtils.isEmpty(userEntity.getUsername()) || EmptyUtils.isEmpty(userEntity.getPassword())
                || EmptyUtils.isEmpty(userEntity.getNickName()) || EmptyUtils.isEmpty(userEntity.getGender())) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        userEntity.setUpdateTime(new Date());
        BCryptPasswordEncoder bcEncoder = new BCryptPasswordEncoder();
        userEntity.setPassword(bcEncoder.encode(userEntity.getPassword()));
        userDao.update(userEntity);
        if (!EmptyUtils.isEmpty(userEntity.getRoleId())) {
            roleDao.updateUr(userEntity.getId(), userEntity.getRoleId());
        }
        WebResponse web = new WebResponse();
        return web;
    }

    /**
     * 根据id集合批量删除用户
     *
     * @param userDelFilter
     * @return
     */
    @Override
    public WebResponse delete(UserDelFilter userDelFilter) {
        if (EmptyUtils.isEmpty(userDelFilter)) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        if (userDelFilter.getIds().size() <= 0) {
            web.setCode(StatusCodeEnum.ERROR.getCode());
            web.setMessage("请选择要删除的用户");
            return web;
        }
        userDao.delete(userDelFilter.getIds());
        return web;
    }

    /**
     * 根绝id查询用户信息
     *
     * @param userEntity
     * @return
     */
    @Override
    public WebResponse queryById(UserEntity userEntity) {
        if (EmptyUtils.isEmpty(userEntity) || EmptyUtils.isEmpty(userEntity.getId())) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        UserEntity user = userDao.queryById(userEntity.getId());
        web.setData(user);
        return web;
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param userEntity
     * @return
     */
    @Override
    public WebResponse queryByUsername(UserEntity userEntity) {
        if (EmptyUtils.isEmpty(userEntity) || EmptyUtils.isEmpty(userEntity.getUsername())) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        UserEntity user = userDao.findByUsername(userEntity.getUsername());
        UserVO userVO = genUserEntity(user);
        web.setData(userVO);
        return web;
    }

    /**
     * 修改用户的封禁状态
     *
     * @param userFilter
     * @return
     */
    @Override
    public WebResponse modifyDelFlag(UserFilter userFilter) {
        if (EmptyUtils.isEmpty(userFilter) || EmptyUtils.isEmpty(userFilter.getId()) || EmptyUtils.isEmpty(userFilter.getDelFlag())) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        UserDTO dto = new UserDTO();
        dto.setId(userFilter.getId());
        dto.setDelFlag(userFilter.getDelFlag());
        dto.setAddBannedTime(new Date());
        userDao.modifyDelFlag(dto);
        return web;
    }

    /**
     * 批量封禁用户
     *
     * @param userFilter
     * @return
     */
    @Override
    public WebResponse bannedUser(UserFilter userFilter) {
        if (EmptyUtils.isEmpty(userFilter)) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        if (userFilter.getIds().size() <= 0) {
            web.setCode(StatusCodeEnum.ERROR.getCode());
            web.setMessage("请选择要封禁的用户");
            return web;
        }
        userDao.batchBannedUser(userFilter.getIds());
        return web;
    }

    /**
     * 批量解封用户
     *
     * @param userFilter
     * @return
     */
    @Override
    public WebResponse unBanUser(UserFilter userFilter) {
        if (EmptyUtils.isEmpty(userFilter)) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        if (userFilter.getIds().size() <= 0) {
            web.setCode(StatusCodeEnum.ERROR.getCode());
            web.setMessage("请选择要解封的用户");
            return web;
        }
        userDao.unBanUser(userFilter.getIds());
        return web;
    }

    /**
     * 修改用户基本信息
     *
     * @param userFilter
     * @return
     */
    @Override
    public WebResponse modifyUserMessage(UserFilter userFilter) {
        if (EmptyUtils.isEmpty(userFilter) || EmptyUtils.isEmpty(userFilter.getEmail()) ||
                EmptyUtils.isEmpty(userFilter.getNickName())) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        String content = "您好，Silence社区的用户，修改资料成功";
        boolean sendMail = MailUtils.sendMail(userFilter.getEmail(), content, "Silence社区");
        if (!sendMail) {
            web.setCode(StatusCodeEnum.ERROR.getCode());
            web.setMessage("请输入正确的邮箱");
            return web;
        }
        userDao.modifyUserMessage(OperUserUtils.getUserId(), userFilter.getEmail(), userFilter.getNickName(), userFilter.getGender(), new Date());
        return web;
    }

    /**
     * 修改用户头像
     *
     * @param userFilter
     * @return
     */
    @Override
    public WebResponse modifyUserPhoto(UserFilter userFilter) {
        if (EmptyUtils.isEmpty(userFilter) || EmptyUtils.isEmpty(userFilter.getPhoto())) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        userDao.modifyUserPhoto(OperUserUtils.getUserId(), userFilter.getPhoto(), new Date());
        return web;
    }

    /**
     * 修改用户密码
     *
     * @param userModifyPwdFilter
     * @return
     */
    @Override
    public WebResponse modifyUserPassword(UserModifyPwdFilter userModifyPwdFilter) {
        if (EmptyUtils.isEmpty(userModifyPwdFilter) || EmptyUtils.isEmpty(userModifyPwdFilter.getNowPassword())
                || EmptyUtils.isEmpty(userModifyPwdFilter.getNewPassword()) || EmptyUtils.isEmpty(userModifyPwdFilter.getReNewPassword())) {
            throw new GradException(StatusCodeEnum.PARAMS_NOT_NULL.getMessage());
        }
        WebResponse web = new WebResponse();
        //确认当前密码是否正确
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        boolean matches = b.matches(userModifyPwdFilter.getNowPassword(), OperUserUtils.getPassword());
        if (!matches) {
            web.setCode(StatusCodeEnum.ERROR.getCode());
            web.setMessage("当前密码输入错误");
            return web;
        }
        //比较新密码和确认密码是否相同，如果不同，则返回提示信息
        if (!userModifyPwdFilter.getNewPassword().equals(userModifyPwdFilter.getReNewPassword())) {
            web.setCode(StatusCodeEnum.ERROR.getCode());
            web.setMessage("两次输入的新密码不一致");
            return web;
        }
        String newPassword = b.encode(userModifyPwdFilter.getNewPassword());
        userDao.modifyUserPassword(OperUserUtils.getUserId(), newPassword, new Date());
        return web;
    }

    /**
     * 处理返回给持久层的参数
     *
     * @param userFilter
     * @return
     */
    private UserDTO genUserFilter(UserFilter userFilter) {
        UserDTO userDTO = new UserDTO();
        Optional.ofNullable(userFilter.getUsername()).ifPresent(userDTO::setUsername);
        Optional.ofNullable(userFilter.getNickName()).ifPresent(userDTO::setNickName);
        Optional.ofNullable(userFilter.getStartTime()).ifPresent(userDTO::setStartTime);
        Optional.ofNullable(userFilter.getEndTime()).ifPresent(userDTO::setEndTime);
        Optional.ofNullable(userFilter.getDelFlag()).ifPresent(userDTO::setDelFlag);
        return userDTO;
    }

    /**
     * 处理返回到前端的数据
     *
     * @param userEntity
     * @return
     */
    private UserVO genUserEntity(UserEntity userEntity) {
        UserVO userVO = new UserVO();
        Optional.ofNullable(userEntity.getId()).ifPresent(userVO::setId);
        Optional.ofNullable(userEntity.getUsername()).ifPresent(userVO::setUsername);
        Optional.ofNullable(userEntity.getNickName()).ifPresent(userVO::setNickName);
        Optional.ofNullable(userEntity.getGender()).ifPresent(userVO::setGender);
        Optional.ofNullable(userEntity.getEmail()).ifPresent(userVO::setEmail);
        Optional.ofNullable(userEntity.getRoleName()).ifPresent(userVO::setRoleName);
        Optional.ofNullable(userEntity.getRoleDesc()).ifPresent(userVO::setRoleDesc);
        Optional.ofNullable(userEntity.getCreateTime()).ifPresent(userVO::setCreateTime);
        Optional.ofNullable(userEntity.getUpdateTime()).ifPresent(userVO::setUpdateTime);
        Optional.ofNullable(userEntity.getAddBannedTime()).ifPresent(userVO::setAddBannedTime);
        Optional.ofNullable(userEntity.getPhoto()).ifPresent(userVO::setPhoto);
        Optional.ofNullable(userEntity.getDelFlag()).ifPresent(userVO::setDelFlag);
        return userVO;
    }

    /**
     * 判断用户是否存在
     *
     * @param username
     * @return
     */
    private boolean exist(String username) {
        UserEntity userEntity = userDao.findByUsername(username);
        return (userEntity != null);
    }
}
