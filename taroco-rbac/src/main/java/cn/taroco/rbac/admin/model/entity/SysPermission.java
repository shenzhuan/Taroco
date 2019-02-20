package cn.taroco.rbac.admin.model.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 *
 * @author liuht
 * 2019/2/19 16:12
 */
@Data
public class SysPermission {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 名称
     */
    @NotEmpty(message = "权限名称不能为空")
    @Size(max = 50, message = "权限名称最长50个字符")
    private String name;

    /**
     * 权限标识
     */
    @NotEmpty(message = "权限标识不能为空")
    @Size(max = 50, message = "权限标识最长50个字符")
    private String code;

    /**
     * 备注
     */
    @Size(max = 255, message = "权限备注最长255个字符")
    private String remarks;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 0-正常，1-删除
     */
    private String delFlag;
}
