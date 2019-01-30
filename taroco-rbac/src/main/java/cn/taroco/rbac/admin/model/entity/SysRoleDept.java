package cn.taroco.rbac.admin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 角色与部门对应关系
 * </p>
 *
 * @author liuht
 * @since 2018-01-20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_role_dept")
public class SysRoleDept extends Model<SysRoleDept> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 角色ID
     */
	@TableField("role_id")
	private Integer roleId;
    /**
     * 部门ID
     */
	@TableField("dept_id")
	private Integer deptId;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SysRoleDept{" +
			", id=" + id +
			", roleId=" + roleId +
			", deptId=" + deptId +
			"}";
	}
}
