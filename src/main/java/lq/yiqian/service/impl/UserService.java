package lq.yiqian.service.impl;

import lq.yiqian.mapper.UserMapper;
import lq.yiqian.domain.User;
import lq.yiqian.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户
 * @author LQ
 * @create 2020-07-04 19:30
 */
@Service
public class UserService implements IUserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 用户登录
     * <p>
     * 检验用户名和密码是否正确
     */
    @Override
    public User login(User user) {
        return userMapper.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
