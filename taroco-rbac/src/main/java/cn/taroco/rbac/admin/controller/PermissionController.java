package cn.taroco.rbac.admin.controller;

import cn.taroco.common.constants.CommonConstant;
import cn.taroco.common.exception.InvalidParamException;
import cn.taroco.common.utils.Query;
import cn.taroco.common.web.BaseController;
import cn.taroco.common.web.Response;
import cn.taroco.rbac.admin.model.entity.SysPermission;
import cn.taroco.rbac.admin.service.SysPermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * 权限管理
 *
 * @author liuht
 * 2019/2/19 16:25
 */
@RestController
@RequestMapping("/permission")
public class PermissionController extends BaseController {

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 分页查询
     *
     * @param params 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    public Response page(@RequestParam Map<String, Object> params) {
        final QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CommonConstant.DEL_FLAG, CommonConstant.STATUS_NORMAL);
        final String codeKey = "code";
        if (params.containsKey(codeKey) && !ObjectUtils.isEmpty(params.get(codeKey))) {
            queryWrapper.like(codeKey, params.get(codeKey));
        }
        return Response.success(permissionService.page(new Query<>(params), queryWrapper));
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @GetMapping("/{id}")
    public Response get(@PathVariable Integer id) {
        return Response.success(permissionService.getById(id));
    }

    /**
     * 根据ID删除
     *
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Integer id) {
        return Response.success(permissionService.deleteById(id));
    }

    /**
     * 新增权限
     *
     * @param permission 权限实体
     * @param result     错误信息
     */
    @PostMapping
    public Response add(@Valid @RequestBody SysPermission permission, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidParamException(result.getAllErrors().get(0).getDefaultMessage());
        }
        return Response.success(permissionService.addPermission(permission));
    }

    /**
     * 编辑权限
     *
     * @param permission 权限实体
     * @param result     错误信息
     */
    @PutMapping
    public Response update(@Valid @RequestBody SysPermission permission, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidParamException(result.getAllErrors().get(0).getDefaultMessage());
        }
        return Response.success(permissionService.updatePermission(permission));
    }
}
