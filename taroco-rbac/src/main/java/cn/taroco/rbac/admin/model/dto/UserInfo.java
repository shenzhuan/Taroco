package cn.taroco.rbac.admin.model.dto;

import cn.taroco.rbac.admin.model.entity.SysUser;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liuht
 * @date 2017/11/11
 * <p>
 */
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -4277243538989343400L;
    /**
     * 用户基本信息
     */
    private SysUser sysUser;
    /**
     * 权限标识集合
     */
    private String[] permissions;

    /**
     * 角色集合
     */
    private String[] roles;
}
