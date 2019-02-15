package cn.taroco.gateway.feign.fallback;

import cn.taroco.common.entity.SysLog;
import cn.taroco.gateway.feign.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author liuht
 * @date 2018/9/13 17:09
 */
@Slf4j
@Service
public class SysLogServiceFallbackImpl implements SysLogService {

    @Override
    public void add(SysLog sysLog) {
        log.error("调用{}异常{}", "addSysLog", sysLog);
    }
}
