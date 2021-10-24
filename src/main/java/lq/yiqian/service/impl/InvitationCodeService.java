package lq.yiqian.service.impl;

import com.github.pagehelper.PageHelper;
import lombok.extern.log4j.Log4j2;
import lq.yiqian.dao.InvitationCodeDao;
import lq.yiqian.dao.query.InvitationCodeQuery;
import lq.yiqian.mapper.InvitationCodeMapper;
import lq.yiqian.domain.InvitationCode;
import lq.yiqian.service.IInvitationCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 邀请码
 *
 * @author LQ
 * @create 2020-06-24 17:36
 */
@Service
@Log4j2
public class InvitationCodeService implements IInvitationCodeService {
    @Resource
    private InvitationCodeMapper invitationCodeMapper;

    @Resource
    private InvitationCodeDao invitationCodeDao;

    @Override
    public List<InvitationCode> findAll() {
        return invitationCodeMapper.findAll();
    }

    /**
     * 根据邀请码查询
     */
    @Override
    public InvitationCode findById(String invitationCodeId) {
        return invitationCodeMapper.findById(invitationCodeId);
    }

    /**
     * 更新总数和剩余次数
     */
    @Override
    public void updateById_sum_last(String invitationCodeId) {
        invitationCodeMapper.updateById_sum_last(invitationCodeId);
    }

    /**
     * 根据邀请码或者用户名或者userId查询
     * <p>
     * 模糊查询
     */
    @Override
    public List<InvitationCode> findByCondition(String condition, Integer page, int size) {
        PageHelper.startPage(page, size);
        return invitationCodeMapper.findByCondition("%" + condition + "%");
    }

    /**
     * 根据id删除
     */
    @Override
    public void deleteById(String id) {
        invitationCodeMapper.deleteById(id);
    }

    /**
     * 产生一个可用的邀请码
     */
    @Override
    public String createInvitationCode() {
        // 产生邀请码
        String invitationCode = this.getRandomString();
        // 判断邀请码是否已经存在
        while (invitationCodeMapper.findById(invitationCode) != null) {
            // 存在继续产生
            invitationCode = getRandomString();
        }
        // 不存在代表这个邀请码可用, 直接返回
        return invitationCode;
    }

    /**
     * 产生一个随机的邀请码
     */
    private String getRandomString() {
        String str = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int number = random.nextInt(36);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 存储邀请码
     */
    @Override
    public void save(String invitationCode, String username, String userId, Integer accountType) {
        String accountTypeStr = "";
        if (accountType == 0) {
            accountTypeStr = "QQ";
        } else if (accountType == 1) {
            accountTypeStr = "微信";
        } else if (accountType == 2) {
            accountTypeStr = "淘宝";
        } else if (accountType == 3) {
            accountTypeStr = "其他";
        }
        InvitationCode code = new InvitationCode();
        code.setInvitationCode(invitationCode);
        code.setUsername(username);
        code.setUserId(userId);
        code.setAccountType(accountTypeStr);
        code.setSum(0);
        code.setCreateDate(new Date());
        code.setLast(3);
        invitationCodeMapper.save(code);
    }

    /**
     * 更新邀请码
     * <p>
     * 用户名 账户类型 登记总数 今日剩余登记数
     */
    @Override
    public void updateInvitationCodeDetials(InvitationCode invitationCode) {
        invitationCodeMapper.updateById_username_accountType_sum_last(invitationCode);
    }

    @Override
    public List<lq.yiqian.dao.domain.InvitationCode> queryForList(InvitationCodeQuery invitationCodeQuery, int page, int pageSize) {
        if (invitationCodeQuery == null) {
            log.warn("InvitationCodeService.queryForList, 查询入参为空");
            return null;
        }

        invitationCodeQuery.setPage(page);
        invitationCodeQuery.setPageSize(pageSize);
        return invitationCodeDao.queryForList(invitationCodeQuery);
    }
}
