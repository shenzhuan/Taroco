package cn.mallcloud.rbac.admin.controller;

import cn.mallcloud.common.constants.CommonConstant;
import cn.mallcloud.common.utils.Query;
import cn.mallcloud.common.web.BaseController;
import cn.mallcloud.common.web.Response;
import cn.mallcloud.rbac.admin.model.dto.RoleDTO;
import cn.mallcloud.rbac.admin.model.entity.SysRole;
import cn.mallcloud.rbac.admin.service.SysRoleMenuService;
import cn.mallcloud.rbac.admin.service.SysRoleService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author liuht
 * @date 2017/11/5
 */
@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 通过ID查询角色信息
     *
     * @param id ID
     * @return 角色信息
     */
    @GetMapping("/{id}")
    public SysRole role(@PathVariable Integer id) {
        return sysRoleService.selectById(id);
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
        SysRole sysRole = sysRoleService.selectById(id);
        sysRole.setDelFlag(CommonConstant.STATUS_DEL);
        return Response.success(sysRoleService.updateById(sysRole));
    }

    /**
     * 获取角色列表
     *
     * @param deptId  部门ID
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
    public Page rolePage(@RequestParam Map<String, Object> params) {
        return sysRoleService.selectwithDeptPage(new Query<>(params), new EntityWrapper<>());
    }

    /**
     * 更新角色菜单
     *
     * @return success、false
     */
    @PutMapping("/roleMenuUpd")
    public Response roleMenuUpd(@RequestBody Map data) {
        final Integer roleId = Integer.valueOf(data.get("roleId") + "");
        final List<Integer> menuIds = (List<Integer>) data.get("menuIds");
        SysRole sysRole = sysRoleService.selectById(roleId);
        return Response.success(sysRoleMenuService.insertRoleMenus(sysRole.getRoleCode(), roleId, menuIds));
    }
}
