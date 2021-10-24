package lq.yiqian.dao.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 邀请码 实体类
 *
 * @author liqian477
 * @date 2021/10/24 13:55
 */
@Data
public class InvitationCode implements Serializable {

    private static final long serialVersionUID = -49342012429754534L;

    /**
     * 邀请码, 也作为主键
     */
    private String invitationCode;

    /**
     * 用户名, 管理员自己填写的
     */
    private String username;

    /**
     * 唯一标识, 可以使用这个userId来找到这个用户
     */
    private String userId;

    /**
     * 用户名的账户类型, 可以是qq wx tb
     */
    private String accountType;

    /**
     * 邀请码的创建时间
     */
    private Date createDate;

    /**
     * 当前邀请码进行缺书登记的总次数
     */
    private Integer sum;

    /**
     * 当前邀请码今天剩余的登记次数
     */
    private Integer last;

}
