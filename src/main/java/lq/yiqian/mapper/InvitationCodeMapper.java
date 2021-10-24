package lq.yiqian.mapper;

import lq.yiqian.domain.InvitationCode;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 操作邀请码列表(invitation_code)的类
 *
 * @author LQ
 * @create 2020-06-24 17:35
 */
public interface InvitationCodeMapper extends Mapper<InvitationCode> {
    /**
     * 查询所有
     */
    @Select("select invitation_code, username, userId, accountType, createDate, sum, last  " +
            " from invitation_code  " +
            "order by createDate desc")
    List<InvitationCode> findAll();

    /**
     * 根据条件查询
     */
    @Select("select invitation_code, username, userId, accountType, createDate, sum, last  " +
            " from invitation_code where invitation_code like #{condition} or username like #{condition}  or userId like #{condition} " +
            "order by createDate desc")
    List<InvitationCode> findByCondition(String condition);

    /**
     * 根据邀请码查询
     */
    @Select("select invitation_code, username, userId, accountType, createDate, sum, last from invitation_code where invitation_code = #{invitationCode}")
    InvitationCode findById(String invitationCode);

    /**
     * 更新sum和last的值
     * <p>
     * sum++
     * last++
     */
    @Update("update invitation_code set sum=sum+1, last=last-1 where invitation_code = #{invitationCode}")
    void updateById_sum_last(String invitationCode);

    /**
     * 根据id删除
     */
    @Delete("delete from invitation_code where invitation_code = #{id} ")
    void deleteById(String id);

    /**
     * 添加邀请码
     */
    @Insert("insert into invitation_code(invitation_code, username, userId, accountType, createDate, sum, last) " +
            "values(#{invitationCode}, #{username}, #{userId}, #{accountType}, #{createDate}, #{sum}, #{last})")
    void save(InvitationCode code);

    /**
     * 更新邀请码
     * <p>
     * username accountType sum last
     */
    @Update("update invitation_code set username=#{username}, userId=#{userId}, accountType=#{accountType}, sum=#{sum}, last=#{last}" +
            " where invitation_code = #{invitationCode}")
    void updateById_username_accountType_sum_last(InvitationCode invitationCode);
}
