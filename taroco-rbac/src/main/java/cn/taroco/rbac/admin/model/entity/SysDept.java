package cn.taroco.rbac.admin.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 部门管理
 * </p>
 *
 * @author liuht
 * @since 2018-01-22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_dept")
public class SysDept extends Model<SysDept> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dept_id", type = IdType.AUTO)
    private Integer deptId;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 排序
     */
    @TableField("order_num")
    private Integer orderNum;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 是否删除  -1：已删除  0：正常
     */
    @TableField("del_flag")
    private String delFlag;

    @TableField("parent_id")
    private Integer parentId;


    @Override
    protected Serializable pkVal() {
        return this.deptId;
    }

    @Override
    public String toString() {
        return "SysDept{" +
                ", deptId=" + deptId +
                ", name=" + name +
                ", orderNum=" + orderNum +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                "}";
    }
}
