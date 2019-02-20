package cn.taroco.rbac.admin.service;

import cn.taroco.rbac.admin.model.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 权限管理Service
 *
 * @author liuht
 * 2019/2/19 16:21
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 添加权限
     *
     * @param permission
     * @return
     */
    Boolean addPermission(SysPermission permission);

    /**
     * 根据权限ID 删除
     *
     * @param id 权限ID
     * @return
     */
    Boolean deleteById(Integer id);

    /**
     * 更新权限
     *
     * @param permission
     * @return
     */
    Boolean updatePermission(SysPermission permission);
}
