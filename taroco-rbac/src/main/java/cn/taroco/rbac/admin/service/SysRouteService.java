package cn.taroco.rbac.admin.service;

import cn.taroco.common.entity.SysRoute;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 动态路由配置表 服务类
 * </p>
 *
 * @author liuht
 * @since 2018-05-15
 */
public interface SysRouteService extends IService<SysRoute> {

    /**
     * 立即生效配置
     * @return
     */
    Boolean applyZuulRoute();
}
