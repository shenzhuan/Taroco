package cn.taroco.rbac.admin.service.impl;

import cn.taroco.common.constants.CommonConstant;
import cn.taroco.common.exception.InvalidParamException;
import cn.taroco.rbac.admin.mapper.SysPermissionMapper;
import cn.taroco.rbac.admin.model.entity.SysPermission;
import cn.taroco.rbac.admin.service.SysPermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 权限管理Service
 *
 * @author liuht
 * 2019/2/19 16:22
 */
@Service
public class SysPermissionServiceImpl
        extends ServiceImpl<SysPermissionMapper, SysPermission>
        implements SysPermissionService {

    @Override
    public Boolean addPermission(final SysPermission permission) {
        final SysPermission condition = new SysPermission();
        condition.setDelFlag(CommonConstant.STATUS_NORMAL);
        condition.setCode(permission.getCode());
        if (this.count(new QueryWrapper<>(condition)) > 0) {
            throw new InvalidParamException("权限标识: " + permission.getCode() + "已经存在");
        }
        return this.save(permission);
    }

    @Override
    public Boolean deleteById(final Integer id) {
        final SysPermission permission = this.getById(id);
        if (permission == null) {
            throw new InvalidParamException("无效的ID");
        }
        permission.setDelFlag(CommonConstant.STATUS_DEL);
        return this.updateById(permission);
    }

    @Override
    public Boolean updatePermission(final SysPermission permission) {
        if (permission.getId() == null) {
            throw new InvalidParamException("ID不存在");
        }
        final QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", permission.getCode());
        queryWrapper.notIn("id", permission.getId());
        if (this.count(queryWrapper) > 0) {
            throw new InvalidParamException("权限标识: " + permission.getCode() + "已经存在");
        }
        return this.updateById(permission);
    }
}
