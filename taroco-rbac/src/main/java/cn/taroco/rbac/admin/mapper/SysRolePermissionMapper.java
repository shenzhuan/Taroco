package cn.taroco.rbac.admin.mapper;

import cn.taroco.rbac.admin.model.entity.SysRolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * 角色 权限 关联 mapper
 *
 * @author liuht
 * 2019/2/19 16:40
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {

    /**
     * 查询角色权限
     *
     * @param roleId 角色ID
     * @return
     */
    @Select("SELECT\n" +
            "\tp.`code`\n" +
            "FROM\n" +
            "\tsys_role_permission rp\n" +
            "LEFT JOIN sys_permission p ON rp.permission_id = p.id\n" +
            "WHERE\n" +
            "\trp.role_id = 1")
    Set<String> getRolePermissions(@Param("roleId") Integer roleId);
}
