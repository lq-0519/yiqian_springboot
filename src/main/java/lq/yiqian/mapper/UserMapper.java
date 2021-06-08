package lq.yiqian.mapper;

import lq.yiqian.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * 用户
 * @author LQ
 * @create 2020-07-04 19:30
 */
public interface UserMapper extends Mapper<User> {
    /**
     * 根据用户名和密码查询
     */
    @Select("select username, password from user " +
            " where username = #{username} and password = #{password}")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
