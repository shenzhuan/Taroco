package cn.taroco.rbac.admin.service;

import cn.taroco.rbac.admin.model.entity.SysRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.Set;

/**
 * 角色权限
 *
 * @author liuht
 * 2019/2/19 17:10
 */
public interface SysRolePermissionService extends IService<SysRolePermission> {

    /**
     * 新增角色权限
     *
     * @param roleId        角色ID
     * @param permissionIds 权限ID列表
     * @return
     */
    Boolean insertRolePermissions(Integer roleId, Collection<Integer> permissionIds);

    /**
     * 查询角色权限
     *
     * @param roleId
     * @return
     */
    Set<String> getRolePermissions(Integer roleId);
}
