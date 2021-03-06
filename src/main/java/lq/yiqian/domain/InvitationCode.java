package lq.yiqian.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 存储每一个邀请码信息
 * @author LQ
 * @create 2020-06-24 15:39
 */
@Table(name = "invitation_code")
public class InvitationCode implements Serializable {
    /**
     * 邀请码, 也作为主键
     */
    @Id
    @Column(name = "invitation_code")
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

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getLast() {
        return last;
    }

    public void setLast(Integer last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return "InvitationCode{" +
                "invitationCode='" + invitationCode + '\'' +
                ", username='" + username + '\'' +
                ", userId='" + userId + '\'' +
                ", accountType='" + accountType + '\'' +
                ", createDate=" + createDate +
                ", sum=" + sum +
                ", last=" + last +
                '}';
    }
}
