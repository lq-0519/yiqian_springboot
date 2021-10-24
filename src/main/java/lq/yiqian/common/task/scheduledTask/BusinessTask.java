package lq.yiqian.common.task.scheduledTask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时器任务
 *
 * @author liqian477
 * @date 2021/10/24 13:27
 */
@Component
public class BusinessTask {


    /**
     * 更新用户可登记数量
     * <p>
     * 每天凌晨一点更新
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void canRegisterNumJob() {
        // 查出last不等于3的id集合
        // 根据ID集合更新
    }
}
