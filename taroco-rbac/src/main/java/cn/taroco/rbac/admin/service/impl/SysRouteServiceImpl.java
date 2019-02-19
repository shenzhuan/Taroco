package cn.taroco.rbac.admin.service.impl;

import cn.taroco.common.constants.CommonConstant;
import cn.taroco.common.entity.SysRoute;
import cn.taroco.common.redis.template.TarocoRedisRepository;
import cn.taroco.common.utils.JsonUtils;
import cn.taroco.rbac.admin.mapper.SysRouteMapper;
import cn.taroco.rbac.admin.service.SysRouteService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 动态路由配置表 服务实现类
 *
 * @author liuht
 * @since 2018-05-15
 */
@Service
public class SysRouteServiceImpl extends ServiceImpl<SysRouteMapper, SysRoute> implements SysRouteService {

    @Autowired
    private TarocoRedisRepository redisRepository;

    /**
     * 同步路由配置信息,到服务网关
     *
     * @return 同步成功
     */
    @Override
    public Boolean applyZuulRoute() {
        QueryWrapper<SysRoute> wrapper = new QueryWrapper<>();
        wrapper.eq(CommonConstant.DEL_FLAG, CommonConstant.STATUS_NORMAL);
        List<SysRoute> routeList = list(wrapper);
        redisRepository.set(CommonConstant.ROUTE_KEY, JsonUtils.toJsonString(routeList));
        return Boolean.TRUE;
    }
}
