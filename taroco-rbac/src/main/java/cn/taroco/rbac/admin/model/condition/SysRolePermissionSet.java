package cn.taroco.rbac.admin.model.condition;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * 角色权限设置条件
 *
 * @author liuht
 * 2019/2/19 17:20
 */
@Data
public class SysRolePermissionSet {

    /**
     * 角色ID
     */
    @NotNull(message = "角色ID不能为空")
    private Integer roleId;

    /**
     * 权限ID
     */
    private Collection<Integer> permissionIds;
}
