package lq.yiqian.common.task.scheduledTask;

import lombok.extern.log4j.Log4j2;
import lq.yiqian.domain.InvitationCode;
import lq.yiqian.mapper.InvitationCodeMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * 定时器任务
 *
 * @author liqian477
 * @date 2021/10/24 15:49
 */
@Component
@Log4j2
public class BusinessTask {

    @Resource
    private InvitationCodeMapper invitationCodeMapper;

    /**
     * 更新用户可登记数量
     * <p>
     * 每天凌晨一点更新
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateLastNumJob() {
        Example example = new Example(InvitationCode.class);
        example.createCriteria()
                .andNotEqualTo("last", 3);
        InvitationCode invitationCode = new InvitationCode();
        invitationCode.setLast(3);
        int num = invitationCodeMapper.updateByExampleSelective(invitationCode, example);
        log.warn("canRegisterNumJob, 更新条数:{}", num);
    }


    /**
     * 测试定时任务
     */
//    @Scheduled(cron = "*/3 * * * * ?")
    public void testScheduledTask() {
        log.warn("testScheduledTask, 测试定时任务:{}", System.currentTimeMillis());
    }
}
