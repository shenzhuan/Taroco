package cn.taroco.rbac.admin.model.entity;

import lombok.Data;

/**
 * 角色权限关联表
 *
 * @author liuht
 * 2019/2/19 16:38
 */
@Data
public class SysRolePermission {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 权限ID
     */
    private Integer permissionId;
}
