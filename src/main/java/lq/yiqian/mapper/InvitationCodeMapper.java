package lq.yiqian.mapper;

import lq.yiqian.domain.InvitationCode;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 操作邀请码列表(invitationCode)的类
 *
 * @author LQ
 * @create 2020-06-24 17:35
 */
public interface InvitationCodeMapper extends Mapper<InvitationCode> {
    /**
     * 查询所有
     */
    @Select("select invitationCode, username, userId, accountType, createDate, sum, last  " +
            " from invitationCode  " +
            "order by createDate desc")
    List<InvitationCode> findAll();

    /**
     * 根据条件查询
     */
    @Select("select invitationCode, username, userId, accountType, createDate, sum, last  " +
            " from invitationCode where invitationCode like #{condition} or username like #{condition}  or userId like #{condition} " +
            "order by createDate desc")
    List<InvitationCode> findByCondition(String condition);

    /**
     * 根据邀请码查询
     */
    @Select("select invitationCode, username, userId, accountType, createDate, sum, last from invitationCode where invitationCode = #{invitationCodeId}")
    InvitationCode findById(String invitationCodeId);

    /**
     * 更新sum和last的值
     * <p>
     * sum++
     * last++
     */
    @Update("update invitationCode set sum=sum+1, last=last-1 where invitationCode = #{invitationCodeId}")
    void updateById_sum_last(String invitationCodeId);

    /**
     * 根据id删除
     */
    @Delete("delete from invitationCode where  invitationCode = #{id} ")
    void deleteById(String id);

    /**
     * 添加邀请码
     */
    @Insert("insert into invitationCode(invitationCode, username, userId, accountType, createDate, sum, last) " +
            "values(#{invitationCode}, #{username}, #{userId}, #{accountType}, #{createDate}, #{sum}, #{last})")
    void save(InvitationCode code);

    /**
     * 更新邀请码
     * <p>
     * username accountType sum last
     */
    @Update("update invitationCode set username=#{username}, userId=#{userId}, accountType=#{accountType}, sum=#{sum}, last=#{last}" +
            " where invitationCode = #{invitationCode}")
    void updateById_username_accountType_sum_last(InvitationCode invitationCode);
}
