package cn.taroco.rbac.admin.controller;

import cn.taroco.common.constants.CommonConstant;
import cn.taroco.common.entity.SysRoute;
import cn.taroco.common.utils.Query;
import cn.taroco.common.web.BaseController;
import cn.taroco.common.web.Response;
import cn.taroco.rbac.admin.service.SysRouteService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 动态路由配置表 前端控制器
 * </p>
 *
 * @author liuht
 * @since 2018-05-15
 */
@RestController
@RequestMapping("/route")
public class RouteController extends BaseController {
    @Autowired
    private SysRouteService sysRouteService;
    /**
     * 通过ID查询
     *
     * @param id ID
     * @return SysRoute
     */
    @GetMapping("/{id}")
    public SysRoute get(@PathVariable Integer id) {
        return sysRouteService.getById(id);
    }

    /**
     * 分页查询信息
     *
     * @param params 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    public Page page(@RequestParam Map<String, Object> params) {
        final QueryWrapper<SysRoute> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CommonConstant.DEL_FLAG, CommonConstant.STATUS_NORMAL);
        return (Page) sysRouteService.page(new Query<>(params), queryWrapper);
    }

    /**
     * 添加
     *
     * @param sysRoute 实体
     * @return success/false
     */
    @PostMapping
    public Response add(@RequestBody SysRoute sysRoute) {
        return Response.success(sysRouteService.save(sysRoute));
    }

    /**
     * 删除
     *
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Integer id) {
        SysRoute sysRoute = new SysRoute();
        sysRoute.setId(id);
        sysRoute.setUpdateTime(new Date());
        sysRoute.setDelFlag(CommonConstant.STATUS_DEL);
        return Response.success(sysRouteService.updateById(sysRoute));
    }

    /**
     * 编辑
     *
     * @param sysRoute 实体
     * @return success/false
     */
    @PutMapping
    public Response edit(@RequestBody SysRoute sysRoute) {
        sysRoute.setUpdateTime(new Date());
        return Response.success(sysRouteService.updateById(sysRoute));
    }

    /**
     * 刷新配置
     *
     * @return success/fasle
     */
    @GetMapping("/apply")
    public Response apply() {
        return Response.success(sysRouteService.applyZuulRoute());
    }
}
