package cn.taroco.rbac.admin.model.dto;

import cn.taroco.rbac.admin.model.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author liuht
 * @date 2017/11/5
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends SysUser {

    private static final long serialVersionUID = 7036595309620126662L;

    @NotNull(message = "用户部门不能为空")
    private Integer deptId;

    /**
     * 新密码
     */
    private String newpassword1;
}
