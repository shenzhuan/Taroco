package cn.taroco.rbac.admin.controller;

import cn.taroco.common.constants.CommonConstant;
import cn.taroco.common.utils.Query;
import cn.taroco.common.web.BaseController;
import cn.taroco.common.web.Response;
import cn.taroco.rbac.admin.model.condition.SysRoleMenuSet;
import cn.taroco.rbac.admin.model.condition.SysRolePermissionSet;
import cn.taroco.rbac.admin.model.dto.RoleDTO;
import cn.taroco.rbac.admin.model.entity.SysRole;
import cn.taroco.rbac.admin.service.SysRoleMenuService;
import cn.taroco.rbac.admin.service.SysRolePermissionService;
import cn.taroco.rbac.admin.service.SysRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author liuht
 * @date 2017/11/5
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    /**
     * 通过ID查询角色信息
     *
     * @param id ID
     * @return 角色信息
     */
    @GetMapping("/{id}")
    public SysRole role(@PathVariable Integer id) {
        return sysRoleService.getById(id);
    }

    /**
     * 添加角色
     *
     * @param roleDto 角色信息
     * @return success、false
     */
    @PostMapping
    public Response role(@RequestBody RoleDTO roleDto) {
        return Response.success(sysRoleService.insertRole(roleDto));
    }

    /**
     * 修改角色
     *
     * @param roleDto 角色信息
     * @return success/false
     */
    @PutMapping
    public Response roleUpdate(@RequestBody RoleDTO roleDto) {
        return Response.success(sysRoleService.updateRoleById(roleDto));
    }

    @DeleteMapping("/{id}")
    public Response roleDel(@PathVariable Integer id) {
        SysRole sysRole = sysRoleService.getById(id);
        sysRole.setDelFlag(CommonConstant.STATUS_DEL);
        return Response.success(sysRoleService.updateById(sysRole));
    }

    /**
     * 获取角色列表
     *
     * @param deptId 部门ID
     * @return 角色列表
     */
    @GetMapping("/roleList/{deptId}")
    public List<SysRole> roleList(@PathVariable Integer deptId) {
        return sysRoleService.selectListByDeptId(deptId);

    }

    /**
     * 分页查询角色信息
     *
     * @param params 分页对象
     * @return 分页对象
     */
    @GetMapping("/rolePage")
    public IPage<RoleDTO> rolePage(@RequestParam Map<String, Object> params) {
        return sysRoleService.selectPageVo(new Query<>(params), (String) params.get("roleName"));
    }

    /**
     * 更新角色菜单
     *
     * @return success、false
     */
    @PutMapping("/roleMenuUpd")
    public Response roleMenuUpd(@Valid @RequestBody SysRoleMenuSet menuSet) {
        return Response.success(sysRoleMenuService.insertRoleMenus(menuSet.getRoleId(), menuSet.getMenuIds()));
    }

    /**
     * 更新角色权限
     *
     * @return success、false
     */
    @PutMapping("/permissions")
    public Response rolePermissions(@Valid @RequestBody SysRolePermissionSet permissionSet) {
        return Response.success(sysRolePermissionService.insertRolePermissions(
                permissionSet.getRoleId(), permissionSet.getPermissionIds()));
    }
}
