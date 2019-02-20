package cn.taroco.rbac.admin.service.impl;

import cn.taroco.rbac.admin.mapper.SysRolePermissionMapper;
import cn.taroco.rbac.admin.model.entity.SysRolePermission;
import cn.taroco.rbac.admin.service.SysRolePermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 角色权限 Service
 *
 * @author liuht
 * 2019/2/19 17:12
 */
@Service
public class SysRolePermissionServiceImpl
        extends ServiceImpl<SysRolePermissionMapper, SysRolePermission>
        implements SysRolePermissionService {

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public Boolean insertRolePermissions(final Integer roleId, final Collection<Integer> permissionIds) {
        // 删除之前的角色权限
        final SysRolePermission condition = new SysRolePermission();
        condition.setRoleId(roleId);
        this.remove(new QueryWrapper<>(condition));

        if (CollectionUtils.isEmpty(permissionIds)) {
            return true;
        }

        List<SysRolePermission> rolePermissions = new ArrayList<>(permissionIds.size());
        permissionIds.forEach(permissionId -> {
            final SysRolePermission permission = new SysRolePermission();
            permission.setRoleId(roleId);
            permission.setPermissionId(permissionId);
            rolePermissions.add(permission);
        });

        return this.saveBatch(rolePermissions);
    }

    @Override
    public Set<String> getRolePermissions(final Integer roleId) {
        return sysRolePermissionMapper.getRolePermissions(roleId);
    }
}
