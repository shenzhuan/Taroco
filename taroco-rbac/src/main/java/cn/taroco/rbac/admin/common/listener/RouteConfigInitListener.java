package cn.taroco.rbac.admin.common.listener;

import cn.taroco.common.constants.CommonConstant;
import cn.taroco.common.entity.SysZuulRoute;
import cn.taroco.common.redis.template.TarocoRedisRepository;
import cn.taroco.common.utils.JsonUtils;
import cn.taroco.rbac.admin.service.SysZuulRouteService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * @author liuht
 * @date 2018/5/16
 */
@Slf4j
@Component
public class RouteConfigInitListener implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    private TarocoRedisRepository redisRepository;

    @Autowired
    private SysZuulRouteService sysZuulRouteService;

    /**
     * Callback used to run the bean.
     * 初始化路由配置的数据，避免gateway 依赖业务模块
     */
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("开始初始化路由配置数据");
        QueryWrapper<SysZuulRoute> wrapper = new QueryWrapper<>();
        wrapper.eq(CommonConstant.DEL_FLAG, CommonConstant.STATUS_NORMAL);
        List<SysZuulRoute> routeList = sysZuulRouteService.list(wrapper);
        if (!CollectionUtils.isEmpty(routeList)) {
            redisRepository.set(CommonConstant.ROUTE_KEY, JsonUtils.toJsonString(routeList));
            log.info("更新Redis中路由配置数据：{}条", routeList.size());
        }
        log.info("初始化路由配置数据完毕");
    }
}
